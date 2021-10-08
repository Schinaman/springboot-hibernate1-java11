notes from 07/10/2021
- Revisar aulas 318 até 321 para entender melhor a complexidade dos relacionamentos e anotations
- aula 312 tem uns fundamentos que eu preciso entender bem para poder entender bem JPA e Rest API. tipo o relacionamento entre Controllers, Services e Repositories e o que são cada um precisar estar claro na minha cabeça;


notes from 08/10/2021
- Pq na aula 323 no Json minha variavel de pagamento aparece na metade da estrutura em vez de aparecer no final?
{
    "id": 1,
    "moment": "2019-06-20T19:53:07Z",
    "orderStatus": "PAID",
    "client": {
        "id": 1,
        "name": "Maria Brown",
        "email": "maria@gmail.com",
        "phone": "988888888",
        "password": "123456"
    },
    "payment": {
        "id": 1,
        "moment": "2019-06-20T21:53:07Z"
    },
    "item": [
        {
            "quantity": 2,
            "price": 90.5,
            "product": {
                "id": 1,
                "name": "The Lord of the Rings",
                "descrption": "Lorem ipsum dolor sit amet, consectetur.",
                "price": 90.5,
                "imgUrl": "",
                "categories": [
                    {
                        "id": 2,
                        "name": "Books"
                    }
                ]
            },
            "subTotal": 181.0
        },
        {
            "quantity": 1,
            "price": 1250.0,
            "product": {
                "id": 3,
                "name": "Macbook Pro",
                "descrption": "Nam eleifend maximus tortor, at mollis.",
                "price": 1250.0,
                "imgUrl": "",
                "categories": [
                    {
                        "id": 3,
                        "name": "Computers"
                    }
                ]
            },
            "subTotal": 1250.0
        }
    ],
    "total": 1431.0
}