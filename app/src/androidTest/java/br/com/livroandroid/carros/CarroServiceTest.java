package br.com.livroandroid.carros;

import android.test.AndroidTestCase;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;

/**
 * Created by jordy on 12/11/15.
 */
public class CarroServiceTest extends AndroidTestCase {
    public void testGetCarros() throws IOException
    {
        List<Carro> carros = CarroService.getCarrosFromWebService(getContext(), "esportivos");
        assertNotNull(carros);
        //precisa retornar esportivos
        assertTrue(carros.size() == 10);
        //Valida informações do primeiro carro
        Carro c0 = carros.get(0);
        assertEquals("Ferrari FF",c0.nome);
        assertEquals("44.532218",c0.latitude);
        assertEquals("10.864019",c0.longitude);
    }
}
