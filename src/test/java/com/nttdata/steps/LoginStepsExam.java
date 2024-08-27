package com.nttdata.steps;

import com.nttdata.page.LoginPageExam;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import static com.nttdata.core.DriverManager.screenShot;

public class LoginStepsExam {

    private WebDriver driver;
    private int quantitySelected; // Para almacenar la cantidad seleccionada

    // Constructor
    public LoginStepsExam(WebDriver driver) {
        this.driver = driver;
        this.quantitySelected = 1; // Inicialmente, siempre es 1
    }

    public int getQuantitySelected() {
        return this.quantitySelected;
    }

    public void login(String email, String password) {
        // Ingresar el email
        driver.findElement(LoginPageExam.emailInput).sendKeys(email);
        screenShot(); // Captura de pantalla después de ingresar el email

        // Ingresar la contraseña
        driver.findElement(LoginPageExam.passwordInput).sendKeys(password);
        screenShot(); // Captura de pantalla después de ingresar la contraseña

        // Hacer clic en el botón de inicio de sesión
        driver.findElement(LoginPageExam.loginButton).click();
        screenShot(); // Captura de pantalla después de hacer clic en el botón
    }

    public void navigateToCategoryAndSubcategory() {
        WebElement categoryElement = driver.findElement(LoginPageExam.clothesCategory);
        categoryElement.click();
        screenShot(); // Captura de pantalla después de seleccionar la categoría

        WebElement subcategoryElement = driver.findElement(LoginPageExam.menSubcategory);
        subcategoryElement.click();
        screenShot(); // Captura de pantalla después de seleccionar la subcategoría
    }

    public void selectFirstProduct() {
        WebElement firstProductElement = driver.findElement(LoginPageExam.firstProduct);
        firstProductElement.click();
        screenShot(); // Captura de pantalla después de seleccionar el primer producto
    }

    public void setQuantity(int quantity) {
        WebElement quantityInputElement = driver.findElement(LoginPageExam.quantityInput);
        try {
            quantityInputElement.clear();
            screenShot(); // Captura de pantalla después de borrar el campo de cantidad
            Thread.sleep(500);
            if (!quantityInputElement.getAttribute("value").isEmpty()) {
                quantityInputElement.clear();
                screenShot(); // Captura de pantalla después de borrar el campo de cantidad
            }
            quantityInputElement.sendKeys(String.valueOf(quantity));
            screenShot(); // Captura de pantalla después de ingresar la nueva cantidad
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void addProductToCart() {
        WebElement addToCartButtonElement = driver.findElement(LoginPageExam.addToCartButton);
        addToCartButtonElement.click();
        screenShot(); // Captura de pantalla después de agregar el producto al carrito
    }

    public boolean isProductAddedConfirmationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera hasta 10 segundos
        try {
            WebElement confirmationPopup = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(LoginPageExam.confirmationPopup)
            );
            boolean isDisplayed = confirmationPopup.isDisplayed();
            screenShot(); // Captura de pantalla después de verificar la confirmación
            return isDisplayed;
        } catch (NoSuchElementException e) {
            // El elemento no se encontró
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Captura cualquier otra excepción
            e.printStackTrace();
            return false;
        }
    }

    public boolean isProductTotalAmountCorrect(int quantity) {
        try {
            WebElement productPriceElement = driver.findElement(LoginPageExam.productPricePopup);
            WebElement totalAmountElement = driver.findElement(LoginPageExam.totalAmountPopup);

            // Obtener el precio del producto y el monto total
            String productPriceText = productPriceElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");
            String totalAmountText = totalAmountElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");

            // Convertir el texto en números
            NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
            Number productPrice = format.parse(productPriceText);
            Number totalAmount = format.parse(totalAmountText);

            // Calcular el monto total esperado
            double expectedTotalAmount = productPrice.doubleValue() * quantity;

            // Comparar el monto total calculado con el monto total del popup
            boolean isCorrect = Math.abs(expectedTotalAmount - totalAmount.doubleValue()) < 0.01; // Tolerancia para errores de redondeo
            screenShot(); // Captura de pantalla después de verificar el monto total
            return isCorrect;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void finalizarCompra() {
        WebElement finalizarCompraButton = driver.findElement(LoginPageExam.finalizarCompraButton);
        finalizarCompraButton.click();
        screenShot(); // Captura de pantalla después de hacer clic en finalizar compra
    }

    public boolean validarTituloCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement carritoTitleElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(LoginPageExam.carritoPageTitle)
            );
            String pageTitle = carritoTitleElement.getText();
            boolean isCorrect = pageTitle.equalsIgnoreCase("CARRITO");
            screenShot(); // Captura de pantalla después de verificar el título del carrito
            return isCorrect;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void increaseProductQuantity(int clicks) {
        WebElement increaseQuantityButtonElement = driver.findElement(LoginPageExam.increaseQuantityButton);
        for (int i = 0; i < clicks; i++) {
            increaseQuantityButtonElement.click();
            screenShot(); // Captura de pantalla después de cada clic en aumentar cantidad
        }
        this.quantitySelected = clicks + 1;
        screenShot(); // Captura de pantalla después de aumentar la cantidad
    }

    public boolean isTotalAmountCorrect(int quantity) {
        try {
            // Obtener el precio del producto en el carrito usando los localizadores de LoginPageExam
            WebElement productPriceElement = driver.findElement(LoginPageExam.productPriceInCart);
            WebElement totalAmountElement = driver.findElement(LoginPageExam.totalAmountInCart);

            // Obtener el texto del precio del producto y el monto total
            String productPriceText = productPriceElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");
            String totalAmountText = totalAmountElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");

            // Convertir el texto en números
            NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
            Number productPrice = format.parse(productPriceText);
            Number totalAmount = format.parse(totalAmountText);

            // Calcular el monto total esperado
            double expectedTotalAmount = productPrice.doubleValue() * quantity;

            // Comparar el monto total calculado con el monto total del carrito
            boolean isCorrect = Math.abs(expectedTotalAmount - totalAmount.doubleValue()) < 0.01; // Tolerancia para errores de redondeo
            screenShot(); // Captura de pantalla después de verificar el monto total
            return isCorrect;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
