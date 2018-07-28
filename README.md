# desafios

## Análise de Complexidade de Tempo e Espaço

Deve-se levar em consideração que as operações select, chars criam um array auxiliar ao invés de modificar o próprio. Por isso, são sempre O(n) em espaço.

each_with_index não cria estrutura auxiliar - O(1) em espaço.

### 1.rb

No pior caso, a entrada é composta apenas por espaços. 

Ex:

    ./1.rb '            ' 4
    &32&32&32&32

A complexidade de tempo de spacesPositions é O(n) - a função percorre todo o array e realiza uma comparação constante.

Por criar uma cópia do array cujo tamanho máximo é igual ao tamanho real da string, sua complexidade espacial é também O(n).

    def spacesPositions(string, string_len)
      0.upto(string_len-1).select { |i| string[i] == ' ' }
    end

O único outro bloco de código que não é O(1) em espaço e tempo é o loop while na chamada principal.

O loop while percorre todo o array (string) e realiza dentro dele somente operações constantes em espaço e tempo (O(1)).

Portanto, a complexidade espacial e temporal do algoritmo completo é O(n).

### 2.rb

A função

    def upToTwoThirdsJumbled(string1, string2)
    
1. Converte a string em um array de caracteres (O(n) em tempo e espaço)
2. Percorre o resultado realizando comparações constantes (O(1) em tempo e espaço)

Portanto, sua complexidade temporal e espacial é O(n) em todos casos.

A função

    def isPermutation(string1, string2)
    
1. Também converte a string em um array de caracteres (O(n) em tempo e espaço)
2. Realiza um sort. O sort implementado no Ruby é o Quicksort, que possui complexidade de tempo O(n²) no pior caso e complexidade espacial de O(n).
3. Aplica upToTwoThirdsJumbled, de complexidades O(n)

Portanto, a complexidade espacial do algoritmo é O(n) em todos casos, e a complexidade temporal, por causa do Quicksort, é O(n²) no pior caso.

### 3.rb

Há 2 tipos de operações não-constantes:

1. Conversão de string para array de chars - O(n) em tempo e espaço.
2. Iteração com each_with_index na string realizando operações constantes dentro do loop - O(n) em tempo, O(1) em espaço.

Portanto, complexidade temporal e espacial O(n).

### 5.rb

As operações relevantes são:

1. Criação da lista encadeada com 1 elemento - O(1) em tempo e espaço.
2. Adicionar novo elemento - O(n) em tempo e O(1) em espaço, pois sempre adiciona na cauda e temos apenas acesso à cabeça.
3. Um loop each_with_index que adiciona novo elemento na lista - O(n) em tempo para percorrer o loop, e operação O(n) em tempo dentro do loop. Portanto, O(n²) em tempo e O(1) em espaço.
4. remove_dup merece análise mais profunda:

remove_dup percorre o array, ou seja, itera n vezes.

Em cada iteração, é necessário percorrer o resto do array para achar duplicatas.

Na primeira iteração, o resto do array é percorrido (n-1 operações).

Na segunda, n-2 operações (não se percorre elementos anteriores).

... na vez n-1, 1 operações.

na última (n), 0 operações.

Portanto, a quantidade total de operações é delimitada por

    (n - 1) + (n - 2) + (n - 3) + ... + (n - n)

Agrupando os n:

    n*n - (1 + 2 + 3 ... + n)
    
Tratando as subtrações como uma soma de progressão aritmética:
 
    n² - (1 + n)/2
    
Temos então uma complexidade temporal de O(n² - n/2), que é o mesmo que O(n²).
 
Enfim, por causa da complexidade temporal de remove_dup, a complexidade temporal do algoritmo completo é O(n²).
 
A complexidade espacial é a complexidade espacial da lista encadeada: O(n).
