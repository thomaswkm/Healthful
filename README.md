***ANÁLISIS:***

***La aplicación debería:***

- Crear archivos .txt para las siguientes funcionalidades:


- Fichas(contiene: datos del paciente asociado)
  - Se contempla un solo archivo, donde los datos de cada paciente debería ser un **String**, el cual sera obtenido al separar el archivo por saltos de linea(cada linea corresponde a un paciente)
  - Se accederá buscando por el RUT, el cual debería ser el primer dato de la linea.

- Horas(contiene: horas agendadas, horas disponibles)
  - De igual manera, se contempla un solo archivo, donde se escribirán las horas en orden de llegada, separadas por saltos de línea, asociada a un Paciente y un médico.
  - Debería gestionarse una manera de comparar las horas tomadas con todas las horas que se podrían tomar en un determinado periodo, para así poder mostrar las horas disponibles.
  - Por ahora solo mostrar de manera textual.

- Cuentas(contiene: {rut,contraseña} por cada usuario.)
  - De igual manera, un solo archivo, separados por salto de linea.

- Acceder a los archivos .txt para acceder a las funcionalidades 1 y 2 anteriores.


***CLASES***


1. Paciente:
- Atributos:
    - RUT
    - Contraseña
    - Nombre
    - ArrayList con horas asociadas(en caso de tener más de una, con distintos médicos)


2. Personal
- Atributos:
    - RUT
    - Contraseña
    - Nombre
    - Especialidad
    - ArrayList con horas asociadas.
  

3. GestorArchivo
- Atributos:
    - Rutas


4. Calendario







