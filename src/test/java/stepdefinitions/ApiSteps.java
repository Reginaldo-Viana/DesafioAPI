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

    @When("eu criar um novo usuário com {string} e {string}")
    public void createNewUser(String userName, String passWord) {
        response = accountPage.createUser(userName, passWord);
    }

    @Then("o usuário deve ser criado com sucesso")
    public void verifyUserCreation() {
        assertEquals(201, response.getStatusCode(), "Erro ao criar usuário!");
        assertNotNull(accountPage.getUserID(), "ID do usuário não foi gerado!");
    }

    @Given("que o usuário {string} existe")
    public void verifyUserExists(String userName) {
        username = userName;
        System.out.println("Usuário existe.");
    }

    @When("eu gerar um token de acesso")
    public void generateAccessToken() {
        response = accountPage.generateToken(username, password);
    }

    @Then("o token deve ser gerado com sucesso")
    public void verifyTokenGeneration() {
        assertEquals(200, response.getStatusCode(), "Erro ao gerar token!");
        assertNotNull(accountPage.getToken(), "Token não foi gerado!");
    }

    @Given("que o usuário {string} e {string} existem")
    public void userCredentialsExist(String userName, String passWord) {
        username = userName;
        password = passWord;
        System.out.println("Credenciais do usuário existem.");
    }

    @When("eu verificar a autorização do usuário")
    public void checkUserAuthorization() {
        response = accountPage.checkAuthorization(username, password);
    }

    @Then("o usuário deve estar autorizado")
    public void verifyUserAuthorization() {
        assertEquals(200, response.getStatusCode(), "Erro ao verificar autorização!");
        assertTrue(response.jsonPath().getBoolean("isAuthorized"), "Usuário não está autorizado!");
    }

    @Given("que a API está configurada")
    public void apiIsConfigured() {
        System.out.println("API configurada.");
    }

    @When("eu listar os livros disponíveis")
    public void listAvailableBooks() {
        response = bookStorePage.listBooks();
    }

    @Then("a lista de livros deve ser retornada com sucesso")
    public void verifyBookList() {
        assertEquals(200, response.getStatusCode(), "Erro ao listar livros!");
        System.out.println("Livros disponíveis: " + response.asString());
    }
}

