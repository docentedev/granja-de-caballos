# granja-de-caballos

Toda la logica esta en los Service

Cada Service utiliza sus propios repositories

Si un service requiere otro repository, lo que hace es consumir el servicio que lo expone para mantener la separación de capas

Los Entities solo se requieren en los Repositories

un service recibe y retorna DTOs

Un controller siempre se comunica con DTOs y ResponseEntity

El manejo de errores lo hace el GlobalExceptionHandler

preferir como DTOs los records en vez de los POJO

Siempre que sea posible hacer Records, agregar validaciones a los Request si es posible en los DTOs como que los string no vengan vacios

Todo el codigo en ingles, clases PascalCase, lo demas en camelCase

Solo comentarios y String de mensajes para el negocio en Español