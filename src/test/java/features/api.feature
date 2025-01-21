Feature: API Test Automation
  Testar a automação de APIs para criação de usuário, geração de token, autorização e interação com livros.

  Scenario: Criar um novo usuário
    Given que eu configurei o endpoint da API
    When eu criar um novo usuário com "testUser" e "Test@123"
    Then o usuário deve ser criado com sucesso

  Scenario: Gerar token para o usuário
    Given que o usuário "testUser" existe
    When eu gerar um token de acesso
    Then o token deve ser gerado com sucesso

  Scenario: Verificar autorização do usuário
    Given que o usuário "testUser" e "Test@123" existem
    When eu verificar a autorização do usuário
    Then o usuário deve estar autorizado

  Scenario: Listar livros disponíveis
    Given que a API está configurada
    When eu listar os livros disponíveis
    Then a lista de livros deve ser retornada com sucesso
