package com.dellavia.denistofoli.testwsprotheus;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by denis.tofoli on 27/04/2016.
 */
public class HttpSoap {
    private ArrayList<TpServ> tpServs;

    public ArrayList<TpServ> postThroughHttpUrlConnection() {

        String soapEnvlop = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://192.1.1.45:8085/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ns:GETTPSERV>\n" +
                "         <ns:CCHAVE>?</ns:CCHAVE>\n" +
                "      </ns:GETTPSERV>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL("http://192.1.1.45:8085/WSINOVA.apw").openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);

            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            urlConnection.addRequestProperty("SOAPAction", "http://192.1.1.45:8085/GETTPSERV");
            urlConnection.addRequestProperty("Connection", "Keep-Alive");
            urlConnection.addRequestProperty("Host", "192.1.1.45:8085");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(soapEnvlop.getBytes());
            out.flush();
            out.close();

            //Get Response
            InputStream is ;
            if(urlConnection.getResponseCode()<=400){
                is=urlConnection.getInputStream();
                xmlToObj(is);
            }else{
                // error from server
                is = urlConnection.getErrorStream();
            }
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return tpServs;
    }

    private void xmlToObj(InputStream inputStream){
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        TpServ tpServ = null;

        try{
            tpServs = new ArrayList<TpServ>();

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();

            Log.d("XML","Iniciando PARSE ..." );
            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagname = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("FUNCTPSERV")){
                            tpServ = new TpServ();
                        } else if (tagname.equalsIgnoreCase("CODIGO")){
                            tpServ.setCodigo(parser.nextText());
                        } else if (tagname.equalsIgnoreCase("DESCRICAO")){
                            tpServ.setDescricao(parser.nextText());
                        } else {
                            break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("FUNCTPSERV")){
                            tpServs.add(tpServ);
                        }
                        break;
                    default:
                        break;
                }
               eventType = parser.next();
            }
            Log.d("XML","... PARSE finalizado" );


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

