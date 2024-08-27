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


import org.openqa.selenium.By;



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

        // Ingresar la contraseña
        driver.findElement(LoginPageExam.passwordInput).sendKeys(password);

        // Hacer clic en el botón de inicio de sesión
        driver.findElement(LoginPageExam.loginButton).click();

        // Tomar una captura de pantalla
        screenShot();
    }

    public boolean isWelcomeMessageDisplayed() {
        // Implementa la lógica para verificar el mensaje de bienvenida
        // Asegúrate de que el mensaje de bienvenida esté visible
        // Por ejemplo:
        // return driver.findElement(By.id("welcome-message")).isDisplayed();
        return true; // Placeholder
    }

    public void navigateToCategoryAndSubcategory() {
        WebElement categoryElement = driver.findElement(LoginPageExam.clothesCategory);
        categoryElement.click();
        WebElement subcategoryElement = driver.findElement(LoginPageExam.menSubcategory);
        subcategoryElement.click();
        screenShot();

    }

    public void selectFirstProduct() {
        WebElement firstProductElement = driver.findElement(LoginPageExam.firstProduct);
        firstProductElement.click();
        screenShot();

    }


    public void setQuantity(int quantity) {
        WebElement quantityInputElement = driver.findElement(LoginPageExam.quantityInput);
        try {
            quantityInputElement.clear();
            Thread.sleep(500);
            if (!quantityInputElement.getAttribute("value").isEmpty()) {
                quantityInputElement.clear();
            }
            quantityInputElement.sendKeys(String.valueOf(quantity));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        screenShot();
    }

    public void addProductToCart() {
        WebElement addToCartButtonElement = driver.findElement(LoginPageExam.addToCartButton);
        addToCartButtonElement.click();
        screenShot();
    }

    public boolean isProductAddedConfirmationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera hasta 10 segundos
        try {
            WebElement confirmationPopup = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(LoginPageExam.confirmationPopup)
            );
            return confirmationPopup.isDisplayed();
        } catch (NoSuchElementException e) {
            // El elemento no se encontró
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
            return Math.abs(expectedTotalAmount - totalAmount.doubleValue()) < 0.01; // Tolerancia para errores de redondeo
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }



    public void finalizarCompra() {
        WebElement finalizarCompraButton = driver.findElement(LoginPageExam.finalizarCompraButton);
        finalizarCompraButton.click();
        screenShot();
    }

    public boolean validarTituloCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement carritoTitleElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(LoginPageExam.carritoPageTitle)
            );
            String pageTitle = carritoTitleElement.getText();
            return pageTitle.equalsIgnoreCase("CARRITO");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void increaseProductQuantity(int clicks) {
        WebElement increaseQuantityButtonElement = driver.findElement(LoginPageExam.increaseQuantityButton);
        for (int i = 0; i < clicks; i++) {
            increaseQuantityButtonElement.click();
        }
        this.quantitySelected = clicks + 1;
        screenShot();
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
            return Math.abs(expectedTotalAmount - totalAmount.doubleValue()) < 0.01; // Tolerancia para errores de redondeo
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
