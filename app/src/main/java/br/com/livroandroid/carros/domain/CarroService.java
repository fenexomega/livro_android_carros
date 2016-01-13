package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.HttpHelper;
import livroandroid.lib.utils.IOUtils;
import livroandroid.lib.utils.SDCardUtils;
import livroandroid.lib.utils.XMLUtils;

import static livroandroid.lib.utils.FileUtils.readRawFileString;

/**
 * Created by jordy on 12/1/15.
 */
public class CarroService {
    private static final String TAG = "CarrosService";
    private static final boolean LOG_ON = true;
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";

    public static List<Carro> getCarrosFromWebService(Context context, String tipo) throws IOException {
        String file;
        List<Carro> carros = null;
        String url = URL.replace("{tipo}",tipo);
        file = HttpHelper.doGet(url);
        salvarArquivoNaMemoriaInterna(context,url,file);
//        salvarArquivoNaMemoriaExterna(context,url,file);
        carros = parserJson(file);
        return carros;

    }

    public static List<Carro> getCarros(Context context,String tipo) throws IOException
    {
        List<Carro> carros = getCarrosFromArchive(context, tipo);
        if(carros != null  && carros.size() > 0)
        {
            return carros;
        }
        carros = getCarrosFromWebService(context,tipo);
        return carros;

    }

    private static List<Carro> getCarrosFromArchive(Context context, String tipo) throws IOException {
        String fileName = String.format("carros_%s.json",tipo);
        Log.d(TAG, "Abrindo arquivo: " + fileName);
        // Lê arquivo da memoria interna
        String json = FileUtils.readFile(context,fileName,"UTF-8");
        if(json == null)
        {
            Log.d(TAG, String.format("Arquivo %s não encontrado", fileName));
            return null;
        }
        List<Carro> carros = parserJson(json);

        return carros;
    }

    private static void salvarArquivoNaMemoriaInterna(Context context,String url, String json)
    {
        String fileName = url.substring(url.lastIndexOf('/')+1);
        File file = FileUtils.getFile(context, fileName);
        IOUtils.writeString(file, json);
        Log.d(TAG, "Arquivo salvo: " + file);
    }

    private static void salvarArquivoNaMemoriaExterna(Context context,String url,String json)
    {
        String fileName = url.substring(url.lastIndexOf('/')+1);
        // Cria um arquivo privado
        File f = SDCardUtils.getPrivateFile(context,fileName, Environment.DIRECTORY_DOWNLOADS);
        IOUtils.writeString(f, json);
        Log.d(TAG, "1) Arquivo privado salvo na pasta downlaods: " + f);
        //Cria um arquivo publico
        f = SDCardUtils.getPublicFile(fileName,Environment.DIRECTORY_DOWNLOADS);
        IOUtils.writeString(f,json);
        Log.d(TAG, "2) Arquivo público salvo na pasta downloads: " + f);
    }

    private static List<Carro> parserJson(String json) {
        List<Carro> carros = new ArrayList<Carro>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject obj = root.getJSONObject("carros");
            JSONArray jsonCarros = obj.getJSONArray("carro");
            for (int i = 0; i < jsonCarros.length(); ++i)
            {
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude = jsonCarro.optString("longitude");
                carros.add(c);
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return carros;
    }

    private static String readFile(Context context, String url) throws IOException {
//        switch (tipo)
//        {
//            case "classicos":
//                return readRawFileString(context, R.raw.carros_classicos, "UTF-8");
//            case "esportivos":
//                return readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
//            case "luxo":
//                return readRawFileString(context, R.raw.carros_luxo, "UTF-8");
//            default:
//                return null;
//        }
        return HttpHelper.doGet(url);

    }

    private static List<Carro> parserXML(String xml) {
        List<Carro> carros = new ArrayList<Carro>();
        Element root = XMLUtils.getRoot(xml,"UTF-8");
        //Le todas as tags <carro>
        List<Node> nodeCarros = XMLUtils.getChildren(root,"carro");
        //Insire cada no na lista
        for(Node node: nodeCarros)
        {
            Carro c = new Carro();
            c.nome = XMLUtils.getText(node,"nome");
            c.desc = XMLUtils.getText(node,"desc");
            c.urlFoto = XMLUtils.getText(node,"url_foto");
            c.urlInfo = XMLUtils.getText(node,"url_info");
            c.urlVideo = XMLUtils.getText(node,"url_video");
            c.latitude = XMLUtils.getText(node,"latitude");
            c.longitude = XMLUtils.getText(node,"longitude");
            carros.add(c);
            if(LOG_ON)
            {
                Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto );
            }
        }
        if(LOG_ON)
            Log.d(TAG, "parserXML: registrados " + carros.size() + " carros ");
        return carros;
    }
}
