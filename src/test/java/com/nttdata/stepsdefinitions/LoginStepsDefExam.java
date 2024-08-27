package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.steps.LoginStepsExam;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

public class LoginStepsDefExam {

    private WebDriver driver;
    private LoginStepsExam loginSteps;



    @Dado("que me encuentro en la página de inicio de sesión")
    public void queMeEncuentroEnLaPáginaDeInicioDeSesión() {
        driver = DriverManager.getDriver();
        driver.get("https://qalab.bensg.com/store/es/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fes%2F");
        loginSteps = new LoginStepsExam(driver);
    }

    @Cuando("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String email, String password) {
        loginSteps.login(email, password);
    }



    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        loginSteps.navigateToCategoryAndSubcategory();

    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        loginSteps.selectFirstProduct();
        loginSteps.increaseProductQuantity(cantidad - 1);  // Si quieres agregar 2, necesitas hacer clic una vez
        loginSteps.addProductToCart();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        boolean isConfirmationDisplayed = loginSteps.isProductAddedConfirmationDisplayed();
        Assertions.assertTrue(isConfirmationDisplayed, "El popup de confirmación del producto no está visible.");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        boolean isTotalAmountCorrect = loginSteps.isProductTotalAmountCorrect(loginSteps.getQuantitySelected());
        Assertions.assertTrue(isTotalAmountCorrect, "El monto total calculado es incorrecto.");
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        loginSteps.finalizarCompra();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        boolean isCarritoTitleCorrect = loginSteps.validarTituloCarrito();
        Assertions.assertTrue(isCarritoTitleCorrect, "El título de la página del carrito no es correcto.");

    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        boolean isTotalAmountCorrect = loginSteps.isTotalAmountCorrect(loginSteps.getQuantitySelected());
        Assertions.assertTrue(isTotalAmountCorrect, "El monto total en el carrito es incorrecto.");
    }
}
