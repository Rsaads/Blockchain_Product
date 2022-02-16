# imd0040-final

Model repository for the creation of the Final Project of the Programming Language II course at Instituto Metrópole Digital at UFRN, taught by Prof. Gustavo Leitao.
## Link para vídeo de apresentação
https://youtu.be/dep0B3sSSIY

## Descrição do trabalho

> The intention of this project is to show in simple ways, in API format, the applications of knowledge obtained in the classroom during the course of the course. The topic chosen was a blockchain as a way to register a product.

## Diagrama de classes

> ![alt text](https://github.com/imetropoledigital/imd0040-trabalho-final-rodrigo_gildo/blob/main/Diagrama.png)

## Instruções para build e execução

###Run the system and at the end of the process go to:

- For a better use we recommend that you use Postman.
- Go to `localhost:8080/api/blockchain (using get method)`
  - By accessing this url you will receive the return of your blockchain, if there is one in the system.
  - Go to `localhost:8080/api/blockchain (using post method)`
    - When accessing this url, it will be necessary to put some information in the body of the request, since the data is sent by POST, as below:
    ```
    {
      "name": "Cadeira Gamer MX5 Giratoria Preto e Branco",
      "price": "990",
      "Date": "2022-02-06",
      "attributes": {
        "color": "Preto e Branco",
        "brand": "MyMax"
      }
    }
    ```
    - A new link will be returned to the response where it is possible to visualize the created block.
- Go to `localhost:8080/api/blockchain/{hash}`
  - When informing the Hash, the block of that Hash is returned.
- Go to `localhost:8080/api/mineBlock?difficulty={difficulty}&typeCalc={typeCalc}&hash={hash}`
  - when accessing this url and the parameters, an acquisition will be performed until a block is found that meets the requirements.
