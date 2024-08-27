# LoginExam.feature
#language: es
Característica: Inicio de sesión

  @TestExamen
  Escenario: Inicio de sesión con usuario y contraseña
    Dado que me encuentro en la página de inicio de sesión
    Cuando me logueo con mi usuario "rosario.trillo07@gmail.com" y clave "12345678MyStore"
    Cuando navego a la categoria "Clothes" y subcategoria "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito


