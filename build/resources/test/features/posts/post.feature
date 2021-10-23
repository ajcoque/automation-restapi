
Feature: Consulta de posts en aplicativo web
  Como usuario del aplicativo web
  necesito consultar datos de los posts
  para verificar la informacion almacenada

  Scenario: Consultar un post proporcionando el id del mismo
    Given el usuario se encuentra en la pagina de inicio del aplicativo web
    When el usuario ingrese el id del post que desea consultar
    Then el sistema le muestra la informacion relacionada al post con el id proporcionado