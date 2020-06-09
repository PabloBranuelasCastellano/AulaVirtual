use aula_virtual;
insert into centroseducativos values
(null,"IES GRAN CAPITAN","Pepe","12585");
select * from centroseducativos c2 ;
insert into profesores(ProfesorId,CentroEducativoId ,Nombre ,PrimerApellido ,SegundoApellido ,NIF_NIE ,FechaAlta ,Email ,Usuario ,Password ,EsActivo ) values
(null,1,"Miguel","Herguedas","Heredias","15428562S","1960-02-15","MiguelHistoria@gmail.com","Miguel Herguedas Heredias",md5("MH1"),true),
(null,1,"Javier","Ortega ","Cano","453531Q","2008-03-30","JavierOrtega@gmail.com","Javier Ortega Cano",md5("123JC"),true);
select * from profesores p ;


select * from Profesores where Email='MiguelHistoria@gmail.com' and Password=md5("MH1") and EsActivo=true;

select * from temas t2 ;
insert into Alumnos values
(null,1,"Paco","Peluca","Peluca","135664W",str_to_date("20/12/1940","%d/%m/%Y"),"Paco.Peluca@gamil.com","Paco Peluca Peluca",MD5('Pacop'),true);
select * from alumnos ;

insert into cursosacademicos values
(null,1,"2019/2020",true,"2019/09/15","2020/06/15"),
(null,1,"2015/2016",false,"2015/09/15","2016/06/15");

insert into Materias values
(null,"Historia del Mundo Contemporaneo",1,1),
(null,"Historia del Arte",1,1),
(null,"Economia de la Empresa",1,1);
select * from materias m ;
update materias set Nombre="Historia del Arte" where MateriaId =2;
select * from temas;
insert into Niveles values
(null,1,"1�ESO",true),
(null,1,"4�ESO",true);
select * from niveles;
insert into grupos values
(null,"1�H",1,1,1,1),
(null,"1�A",2,1,1,1),
(null,"1�C",2,2,1,1);
select * from grupos;

insert into gruposalumnos  values
(null,1,1),
(null, 3, 1);
insert into temas values
(null,"La Revoluci�n Industrial","Los cambios que transformaron la forma de vivir y producir en Inglaterra a mediados del siglo XIX",1,1,1,"2020/02/18",true,2,3);
UPDATE aula_virtual.grupos
SET Nombre='1�C', CursoAcademicoId=1, MateriaId=2, NivelId=1, ProfesorId=1
WHERE GrupoId=3;

select distinct t.titulo,m.Nombre from temas t, profesores p ,niveles n,materias m where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=1 and t.materiaId=1);
select pnt.Titulo,pnt.resumen,pnt.texto from puntos pnt,temas t,profesores p where(pnt.temaId=t.temaId and t.profesorId=p.profesorId and t.materiaId= 1 and t.profesorId=1 and pnt.TemaId=2);
select * from Puntos;
update puntos set EsActivo=false where PuntoId=1;
update puntos set EsActivo=false where PuntoId=1 and TemaId=2;

INSERT INTO aula_virtual.temas
(TemaId, Titulo, Resumen, ProfesorId, MateriaId, NivelId, FechaCreacion, EsActivo, Numero, Orden)
VALUES(2, 'La Revolci�n Francesa', 'La Francia de 1799 era totalmente distinta a la de 1789. En apenas una d�cada, la Revoluci�n hab�a creado un estado completamente nuevo. De una monarqu�a absolutista se hab�a pasado a una rep�blica. Ya no hab�a s�bditos, sino ciudadanos. La sociedad, antes capitaneada por aristocracia y clero, ten�a ahora en la burgues�a su motor principal. Tan irreconocible estaba la naci�n y tan ori�ginal era el modo en que se hab�a organizado que hubo de remontarse a la Roma cl�sica para dar nombre a sus nuevas instituciones: Senado, Consulado, Tribunado, Prefectura...

Las leyes y la econom�a, el arte y la ciencia, la educaci�n, el ej�rcito, el papel de la Iglesia, la administraci�n territorial... todos los aspectos del estado hab�an cambiado respecto del Antiguo R�gimen. E, inevitablemente, el modelo de esta renovaci�n integral se tom� como ejemplo en aquellas otras latitudes en que tambi�n se persegu�a la soberan�a del pueblo en los asuntos colectivos, la libertad pol�tica y la igualdad ante la ley. Francia estaba de estreno tras el vendaval revolucionario y el mundo la miraba fascinado.', 1, 1, 1, '2020-06-04', 1, 1, 1);

INSERT INTO aula_virtual.puntos
(PuntoId, TemaId, Titulo, Resumen, Texto, Orden, Numero, EsActivo)
VALUES(1, 2, 'Causas de la Revoluci�n Francesa', 'Principales Causas que provocaron la caida del Antiguo R�gimen y del fin del Absolutismo.', 'Las principales causas de la Revoluci�n francesa fueron las siguientes:

1)Las arbitrariedades de un absolutismo mon�rquico que oprim�a a la mayor�a de sus s�bditos.

2)Una gran desigualdad social debido a las fuertes cargas (impuestos, tributos y diezmo) que reca�an sobre los campesinos franceses, quienes con su trabajo deb�an mantenerse a s� mismos y a los grupos privilegiados: la nobleza y el clero.

3)El descontento de sectores intelectuales por la falta de derechos y libertades. Estos intelectuales estaban muy influidos por las ideas de la Ilustraci�n.

4)La crisis econ�mica y financiera en la que se encontraba Francia. Los excesos de gastos de la Corona y los gastos provenientes de la participaci�n en la guerra por la Independencia de Estados Unidos hab�an provocado un d�ficit presupuestario.

5)Una serie de malas cosechas que provocaron aumentos desmedidos del precio del pan, que era el principal alimento de los sectores populares.

6)Las aspiraciones de una burgues�a en ascenso que deseaba que su posici�n econ�mica se correspondiera con su situaci�n social y sus derechos pol�ticos.', 1, 1, 1);

update temas t, profesores p ,niveles n,materias m set t.EsActivo=false where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=1 and t.materiaId=1 and t.TemaId =1 and n.NivelId =6);
update temas t, profesores p ,niveles n,materias m set t.EsActivo=false where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=1 and t.materiaId=1 and t.TemaId=1 and n.NivelId=1);
update temas t set EsActivo=false where TemaId=2 and profesorId= 1 and NivelId= 1 and MateriaId=1;
INSERT INTO aula_virtual.gruposalumnos
(GrupoAlumnoId, GrupoId, AlumnoId)VALUES(3, 2, 1);

select current_date(); 

insert into cuestionarios values(null,1,"Examen tema 1","Completar las cuestiones siguientes","Prueba",10,1,0.25,current_date() ,true);
select * from cuestionarios;
insert into preguntas values
(null,1,"En que a�o creo James Watt la maquina de vapor",current_date(),5,true),
(null,1,"Principales inventos de la revoluci�n Industrial",current_date(),1,true),
(null,1,"Como afect� la industrializaci�n a la metalurgia",current_date(),10,true);
select * from preguntas p ;
insert into opciones values
(null,1,"1859",false,current_date(),1,true),
(null,1,"1763",true,current_date(),2,true),
(null,1,"1868",false,current_date(),3,true),
(null,2,"1)Lanzadera Volante,Barco de Vapor,Maquina de Vapor",true,current_date(),1,true),
(null,2,"Alumbrado electrico,Altos Hornos,Lanzadera Volante",true,current_date(),2,true),
(null,2,"Produccion en Serie,Nuevos Transportes,Alumbrado Publico",false,current_date(),3,true),
(null,3,"Nueva forma de fabricar metales",true,current_date(),1,true),
(null,3,"Nuevas Aleaciones",true,current_date(),2,true),
(null,3,"Primeros altos Hornos",false,current_date(),3,true);
select * from opciones;

insert into opcionespuntos values
(null,1,3),
(null,2,3),
(null,3,3),
(null,4,4),
(null,5,4),
(null,6,4),
(null,7,4),
(null,8,4),
(null,9,4);
select c.Titulo from cuestionarios c,profesores p where(c.ProfesorId =p.ProfesorId and c.ProfesorId =1);
select Texto from preguntas p where CuestionarioId =1; 
select * from opciones o where PreguntaId=3;
select * from opcionespuntos op ,puntos p,opciones o where(op.OpcionId =o.OpcionId and op.PuntoId =p.PuntoId and o.PreguntaId =3);
