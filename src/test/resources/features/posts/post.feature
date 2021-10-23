
Feature: Consulta de posts en aplicativo web
  Como usuario del aplicativo web
  necesito consultar datos de los posts
  para verificar la informacion almacenada

  Background:
    Given el usuario se encuentra en la pagina de inicio del aplicativo web

  Scenario: Consultar un post proporcionando el id del mismo

    When el usuario ingrese el id del post que desea consultar
    Then el sistema le muestra la informacion relacionada al post con el id proporcionado

  Scenario: Consultar comentarios de un post proporcionando el id del mismo
    //Given el usuario se encuentra en la pagina de inicio del aplicativo web
    When el usuario ingrese el id del post del que desea obtener los comentarios
    Then el sistema le muestra la informacion relacionada con los comentarios del post con el id proporcionado