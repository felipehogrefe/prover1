Programa Prover

O programa foi desenvolvido com o intuito de resolver problemas lógicos, utilizando regras de lógica clássica proposicional, onde temos premissas e uma conclusão, utilizamos regras dessa lógica para obter o resultado.

O objetivo da primeira tarefa era implementar o provador, já para a segunda foi necessário mostrar a sequencia seguida para se chegar no resultado.

A implementação foi feita da seguinte forma: dado um conjunto inicial de premissas (ligadas pelo conectivo E, disjunções) e uma conclusão, o programa aplica as regras criando novas sentenças e as armazenando em uma lista, com isso o programa é capaz de produzir todas as possíveis sentenças deriváveis da primeira.

Para sentenças pequenas não há problema, mas quando temos quatro ou as vezes até três premissas a quantidade de possíveis derivações cresce muito, então foi necessário implementar tecnicas de otimização, dentre as quais podemos citar casos em que as listas são limpas ou a busca de premissas iguais.

Por fim, para alcançar o objetivo de mostrar a sequência percorrida temos uma estrutura de dados em que cada nova sequência produziada pelas regras aponta para o sua anterior.

Exemplo de saída (ordenado de  baixo para cima):

6: T

8: (Tv~A)

3: ((~BvB)v~A)

4: (~Av(~BvB))

6: ((~Av~B)vB)

8: ((T^(~Av~B))vB)

5: (((~AvA)^(~Av~B))vB)

3: ((~Av(A^~B))vB)

0: (((A^~B)v~A)vB)

Onde as regras utilizadas podem ser encontradas em:
https://github.com/felipehogrefe/prover1/blob/master/src/rules
