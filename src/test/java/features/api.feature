Feature: API Test Automation
  Testar a automa��o de APIs para cria��o de usu�rio, gera��o de token, autoriza��o e intera��o com livros.

  Scenario: Criar um novo usu�rio
    Given que eu configurei o endpoint da API
    When eu criar um novo usu�rio com "testUser" e "Test@123"
    Then o usu�rio deve ser criado com sucesso

  Scenario: Gerar token para o usu�rio
    Given que o usu�rio "testUser" existe
    When eu gerar um token de acesso
    Then o token deve ser gerado com sucesso

  Scenario: Verificar autoriza��o do usu�rio
    Given que o usu�rio "testUser" e "Test@123" existem
    When eu verificar a autoriza��o do usu�rio
    Then o usu�rio deve estar autorizado

  Scenario: Listar livros dispon�veis
    Given que a API est� configurada
    When eu listar os livros dispon�veis
    Then a lista de livros deve ser retornada com sucesso
