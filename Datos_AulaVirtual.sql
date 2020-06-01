use aula_virtual;
insert into centroseducativos values
(null,"IES GRAN CAPITAN","Pepe","12585");
select * from centroseducativos c2 ;
insert into profesores(ProfesorId,CentroEducativoId ,Nombre ,PrimerApellido ,SegundoApellido ,NIF_NIE ,FechaAlta ,Email ,Usuario ,Password ,EsActivo ) values
(null,1,"Miguel","Herguedas","Heredias","15428562S","1960-02-15","MiguelHistoria@gmail.com","Miguel Herguedas Heredias",md5("MH1"),true),
(null,1,"Javier","Ortega ","Cano","453531Q","2008-03-30","JavierOrtega@gmail.com","Javier Ortega Cano",md5("123JC"),true);
select * from profesores p ;


select * from Profesores where Email='MiguelHistoria@gmail.com' and Password=md5("MH1") and EsActivo=true;


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
(null,1,"1ºESO",true),
(null,1,"4ºESO",true);
select * from niveles;
insert into grupos values
(null,"1ºH",1,1,1,1),
(null,"1ºA",2,1,1,1),
(null,"1ºC",2,2,1,1);
select * from grupos;

insert into gruposalumnos  values
(null,1,1),
(null, 3, 1);
/*
select * from gruposalumnos g2 ;
select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=1);
select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId ) order by a.PrimerApellido asc;*/
insert into temas values
(null,"La Revolución Industrial","Los cambios que transformaron la forma de vivir y producir en Inglaterra a mediados del siglo XIX",1,1,1,"2020/02/18",true,2,3);
/*select * from gruposalumnos g;
select * from grupos g2 ;
select * from materias;
select * from temas;
select distinct m.Nombre ,n.Denominacion  from niveles n,temas t ,profesores p,materias m where (t.ProfesorId =p.ProfesorId  and p.ProfesorId =1);
select * from temas t,materias m,profesores p where (m.MateriaId =t.MateriaId and t.ProfesorId= p.ProfesorId  and p.ProfesorId =1);

select curdate();
select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =false and P.ProfesorId=1);
select * from materias;
select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =false and P.ProfesorId=1);
select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId and g.GrupoId=1);
select * from gruposalumnos g2 ;
select distinct M.Nombre,m.MateriaId,m.Nombre ,n.Denominacion,n.NivelId  from temas t,niveles n,profesores p,materias m where (t.NivelId =n.NivelId and p.ProfesorId =t.ProfesorId and m.EsActiva =true and P.ProfesorId=1);
select * from gruposalumnos g2 ;
select * from alumnos a2 ;
select distinct M.Nombre as Asignatura  ,a.Usuario as Nombre_Alumno ,g.Nombre from grupos g,materias m ,alumnos a ,gruposalumnos g2 where(g.MateriaId=M.MateriaId and  g2.AlumnoId =a.AlumnoId  and a.AlumnoId =1 and g.GrupoId =3);
select distinct M.Nombre as Asignatura  ,a.Usuario as Nombre_Alumno from grupos g,materias m ,alumnos a ,gruposalumnos g2 where(g.MateriaId=M.MateriaId and  g2.AlumnoId =a.AlumnoId  and a.AlumnoId =1 and g2.GrupoId =2);
select distinct M.Nombre,t.MateriaId,m.Nombre ,n.Denominacion,n.NivelId  from temas t,niveles n,profesores p,materias m where (t.NivelId =n.NivelId and p.ProfesorId =t.ProfesorId and m.EsActiva =true and P.ProfesorId=1);

select m.Nombre, a.Usuario
from alumnos a
inner join gruposalumnos ga on a.AlumnoId = ga.AlumnoId
inner join grupos g on ga.GrupoId = g.GrupoId
inner join materias m on g.MateriaId = m.MateriaId
where (a.AlumnoId = 1 and m.EsActiva =true);
select distinct M.Nombre,M.MateriaId from niveles n,profesores p,materias m where (p.ProfesorId =t.ProfesorId and m.EsActiva =true and P.ProfesorId=1);

select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura,M.MateriaId ,N.Denominacion as NivelEducativo ,n.NivelId,p.ProfesorId ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=1);

select distinct M.Nombre,t.MateriaId,m.Nombre ,n.Denominacion,n.NivelId  from temas t,niveles n,profesores p,materias m where (t.NivelId =n.NivelId and p.ProfesorId =t.ProfesorId and m.EsActiva =true and P.ProfesorId=1);
select t.titulo,m.Nombre from temas t,materias m ,profesores p ,niveles n where(t.MateriaId =m.MateriaId and t.ProfesorId =p.ProfesorId and t.NivelId =n.NivelId  and t.ProfesorId =1 and t.MateriaId =1) ;
select * from temas;*/
select distinct t.titulo,m.Nombre from temas t, profesores p ,niveles n,materias m where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=1 and t.materiaId=1);
select pnt.Titulo,pnt.resumen,pnt.texto from puntos pnt,temas t,profesores p where(pnt.temaId=t.temaId and t.profesorId=p.profesorId and t.materiaId= 1 and t.profesorId=1 and pnt.TemaId=2);
select * from Puntos;
update puntos set EsActivo=false where PuntoId=1;
update puntos set EsActivo=false where PuntoId=1 and TemaId=2;

INSERT INTO aula_virtual.puntos
(PuntoId, TemaId, Titulo, Resumen, Texto, Orden, Numero, EsActivo)
VALUES(1, 2, 'Causas de la Revolución Francesa', 'Principales Causas que provocaron la caida del Antiguo Régimen y del fin del Absolutismo.', 'Las principales causas de la Revolución francesa fueron las siguientes:

1)Las arbitrariedades de un absolutismo monárquico que oprimía a la mayoría de sus súbditos.

2)Una gran desigualdad social debido a las fuertes cargas (impuestos, tributos y diezmo) que recaían sobre los campesinos franceses, quienes con su trabajo debían mantenerse a sí mismos y a los grupos privilegiados: la nobleza y el clero.

3)El descontento de sectores intelectuales por la falta de derechos y libertades. Estos intelectuales estaban muy influidos por las ideas de la Ilustración.

4)La crisis económica y financiera en la que se encontraba Francia. Los excesos de gastos de la Corona y los gastos provenientes de la participación en la guerra por la Independencia de Estados Unidos habían provocado un déficit presupuestario.

5)Una serie de malas cosechas que provocaron aumentos desmedidos del precio del pan, que era el principal alimento de los sectores populares.

6)Las aspiraciones de una burguesía en ascenso que deseaba que su posición económica se correspondiera con su situación social y sus derechos políticos.', 1, 1, 1);
