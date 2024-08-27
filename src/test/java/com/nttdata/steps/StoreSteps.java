package com.nttdata.steps;

import com.nttdata.page.StorePage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreSteps {

    private WebDriver driver;
    private StorePage storePage;
    private WebDriverWait wait;


  //  @Cuando("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int quantity) {
        storePage.selectFirstProduct();
        storePage.setQuantity(quantity);
        storePage.addProductToCart();
    }

//    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmación_del_producto_agregado() {
        // Implementa la validación del popup aquí.
    }

    private void setupDriver() {
        if (driver == null) {
            // Configura el driver de Chrome
            System.setProperty("webdriver.chrome.driver", "ruta/al/chromedriver");  // Ajusta la ruta según tu configuración
            driver = new ChromeDriver();
        }
    }


}
