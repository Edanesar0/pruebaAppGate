# pruebaAppGate

En la solución de: "Indicar las dos prácticas, herramientas, frameworks o acciones que para usted tendrían mejor impacto en
el aseguramiento de la calidad del código o de una aplicaciónAndroid."

Uno de las buenas practicas es tener una buena arquitectura, desde que sea escalable funciona de maravilla por que reduce el mantenimiento que se requiere asi reduciendo costos en nuevos cambios y no sean complicados de realizarlos 

Otra practica es hacer un pentest así asegurando las vulneraciones que puede llegar a tener la aplicación así evitando fallos incensarios y huecos de seguridad 

## Arquitectura de la aplicación ##


#### Dagger2 - DI ####
Para lograr un app más organizada y evitando la creación de objeto con gran coste de intanciamiento
se uso dagger2 para objeto de uso común como las instancias de Retrofit2, Glide la construción de los ViewModels y demás.


#### DataBinding ####
Para tener un código limpio y hacer más eficiente el app se usaron adaptadores junto con databinding
eliminando el instanciamiento de los fragmentos y pasando la resposabilidad directamente a los XML.



#### ViewModels ####
Encargados de la lógica y siendo la comunicación entre los Fragmentos y la única fuente de datos (local database)

--<a href="https://en.wikipedia.org/wiki/Single_source_of_truth">Single source of truth</a>--

## Principio de responsabilidad única ##
Princípio cuyo esquema determina la resposabildad única de cada parte del sistema sin interferir 
en otras clases haciendo un buen uso del encapsulamiento. 
En este proyecto se sigue este princípio de manera eficaz gracias a MVVM y al single Activity.

## Código Limpio ##
El concepto de código limpio puede ser subjetivo y depende del desarrollador, en mi opinión este debe
ser fácil de leer y mantener, con fácil de leer me refiero a que lo nombres de la propiedades, funciones y demás 
deben ser mnemotécnicas y solor se encarguen de los que deben "Principio de responsabilidad única", en cuanto a lo del mantenimiento
la arquitectura del proyecto debe ser escalable y permitir que se pueda hacer pruebas UnitTest ú otros para hallar de manera pronta
bugs antes de que el usuario los encuentre.
  



