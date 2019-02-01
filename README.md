# almundo
Examen de almundo para entrevista
Buenos dias, en la clase DispatcherTest podran encontrar los test para la clase dispatcher solicitada en el examen. En cada test se testea algo especifico de la clase

Test case 1: se llama al dispatcher con una "llamada" y un solo "empleado".

Test case 2: Se llama al dispatcher con diez "llamadas" y diez "empleados" que difieren entre operador, supervisor y director.

Test case 3: Se llama al dispatcher con seis "llamadas" y un solo empleado, dando como solucion el problema planteado de que pasa si entran llamadas y no hay operadores disponibles, en este caso se ponen en cola para ser atendidas luego de que un empleado se desocupe.

Test case 4: Se llama al dispatcher con cinco "llamadas" y sin ningun empleado, si bien es una prueba que no tiene mucho sentido, el proceso no falla ante un problema de inicializacion informando por log porque no se inicio el proceso.

Test case 5: Se llama al dispatcher con diez "llamadas" y quince empleados, de los cuales 10 son operadores, 3 supervisores y 2 directores, y solo los operadores atienden las llamadas.

Test case 6: Se llama al dispatcher con quince "llamadas" y 10 empleados, dando solucion al problema planteado sobre que sucede si llegan mas de 10 llamadas, en este caso 5 pasaran a un estado de espera, hasta que algun empleado disponible lo pueda tomar.
