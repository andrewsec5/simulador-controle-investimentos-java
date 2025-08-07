# simulador-investimentos-java
Simulador de investimentos pessoais em Java (cálculos precisos e juros compostos)

 💰 **Simulador de Investimentos Pessoais**

Simulador em Java para cálculo de rendimento de aplicações financeiras com base em juros compostos mensais.  
Permite ao usuário aplicar valores em Poupança, CDB ou Tesouro Direto, de forma simples e interativa via terminal.

---

   📌 *Funcionalidades*

- ✅ Interface interativa via terminal.
- ✅ Validação de entradas numéricas (evita erros de digitação e permite uso de ',' ou '.' como divisor decimal).
- ✅ Simulação com juros compostos usando *BigDecimal* (evita erros de precisão).
- ✅ Suporte a múltiplos investimentos em sequência.
- ✅ Geração de relatório final com saldo restante e rendimentos

---

  🔒 *Limitações Atuais*

- ❌ Não persiste os dados (as simulações são perdidas ao encerrar o programa).
- ❌ Não suporta investimentos do mesmo tipo com prazos diferentes.

---

   🛠️ *Tecnologias e Conceitos Utilizados*

- *Java 17+*.
- *BigDecimal* para cálculos precisos.
- *Scanner* para entrada de dados do usuário.
- Estruturação com métodos reutilizáveis.
- Validação robusta de entrada com *try/catch* e *regex*.

---

   🚀 *Como Executar*

1. Instale Java 8 ou superior e o Git.
2. Clone este repositório no prompt de comando:
     git clone https://github.com/andrewsec5/simulador-controle-investimentos-java.git
3. Entre na página do projeto:
     cd simulador-controle-investimentos-java
4. Compile o programa:
     javac -d out src/br/com/agibank/hacka/Main.java
5. Execute o programa:
     java -cp out br.com.agibank.hacka.Main

---

 📄 **Licença**

 Este projeto é de uso livre e para fins de aprendizado. Fique à vontade para modificar.

 ---

 🙋 **Autor**

 Me chamo Andrew, estou estudando engenharia de software com foco em *Java* e tecnologia aplicada em resolução de problemas.
 Conecte-se comigo no Linkedin para me ajudar a evoluir e acompanhar minha trajetória profissional:
    https://www.linkedin.com/in/andrew-alves-632725352/
