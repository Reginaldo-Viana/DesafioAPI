package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pages.AccountPage;
import pages.BookStorePage;

import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {
    private final AccountPage accountPage = new AccountPage();
    private final BookStorePage bookStorePage = new BookStorePage();

    private Response response;
    private String username = "testUser";
    private String password = "Test@123";

    @Given("que eu configurei o endpoint da API")
    public void configureApiEndpoint() {
        System.out.println("Endpoint configurado.");
    }

    @When("eu criar um novo usu�rio com {string} e {string}")
    public void createNewUser(String userName, String passWord) {
        response = accountPage.createUser(userName, passWord);
    }

    @Then("o usu�rio deve ser criado com sucesso")
    public void verifyUserCreation() {
        assertEquals(201, response.getStatusCode(), "Erro ao criar usu�rio!");
        assertNotNull(accountPage.getUserID(), "ID do usu�rio n�o foi gerado!");
    }

    @Given("que o usu�rio {string} existe")
    public void verifyUserExists(String userName) {
        username = userName;
        System.out.println("Usu�rio existe.");
    }

    @When("eu gerar um token de acesso")
    public void generateAccessToken() {
        response = accountPage.generateToken(username, password);
    }

    @Then("o token deve ser gerado com sucesso")
    public void verifyTokenGeneration() {
        assertEquals(200, response.getStatusCode(), "Erro ao gerar token!");
        assertNotNull(accountPage.getToken(), "Token n�o foi gerado!");
    }

    @Given("que o usu�rio {string} e {string} existem")
    public void userCredentialsExist(String userName, String passWord) {
        username = userName;
        password = passWord;
        System.out.println("Credenciais do usu�rio existem.");
    }

    @When("eu verificar a autoriza��o do usu�rio")
    public void checkUserAuthorization() {
        response = accountPage.checkAuthorization(username, password);
    }

    @Then("o usu�rio deve estar autorizado")
    public void verifyUserAuthorization() {
        assertEquals(200, response.getStatusCode(), "Erro ao verificar autoriza��o!");
        assertTrue(response.jsonPath().getBoolean("isAuthorized"), "Usu�rio n�o est� autorizado!");
    }

    @Given("que a API est� configurada")
    public void apiIsConfigured() {
        System.out.println("API configurada.");
    }

    @When("eu listar os livros dispon�veis")
    public void listAvailableBooks() {
        response = bookStorePage.listBooks();
    }

    @Then("a lista de livros deve ser retornada com sucesso")
    public void verifyBookList() {
        assertEquals(200, response.getStatusCode(), "Erro ao listar livros!");
        System.out.println("Livros dispon�veis: " + response.asString());
    }
}

