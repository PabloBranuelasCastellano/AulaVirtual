use aula_virtual;
insert into centroseducativos values
(null,"IES GRAN CAPITAN","Pepe","12585");
select * from centroseducativos c2 ;
insert into profesores(ProfesorId,CentroEducativoId ,Nombre ,PrimerApellido ,SegundoApellido ,NIF_NIE ,FechaAlta ,Email ,Usuario ,Password ,EsActivo ) values
(1,1,"Miguel","Herguedas","Heredias","15428562S","1960-02-15","MiguelHistoria@gmail.com","Miguel Herguedas",md5("MH1"),true);
select * from profesores p ;


select * from Profesores where Email='MiguelHistoria@gmail.com' and Password=md5("MH1") and EsActivo=true;


insert into Alumnos values
(null,1,"Paco","Peluca","Peluca","135664W",str_to_date("20/12/1940","%d/%m/%Y"),"Paco.Peluca@gamil.com","Paco Peluca Peluca",MD5('Pacop'),true);
select * from alumnos ;

insert into cursosacademicos values
(null,1,"2019/2020",true,"2019/09/15","2020/06/15"),
(null,1,"2015/2016",false,"2015/09/15","2016/06/15");

insert into Materias values
(null,"Historia del Mundo Contemporaneo",1,1);
select * from materias m ;

insert into Niveles values
(null,1,"1ºESO",true),
(null,1,"4ºESO",true);
select * from niveles;
insert into grupos values
(null,"1ºH",1,1,1,1),
(null,"1ºA",2,1,1,1);
select * from grupos;
insert into gruposalumnos  values
(null,1,1);
select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and P.ProfesorId=1);
select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId ) order by a.PrimerApellido asc;
insert into temas values
(null,"La Revolución Industrial","Los cambios que transformaron la forma de vivir y producir en Inglaterra a mediados del siglo XIX",1,1,1,"2020/02/18",true,2,3);
select * from gruposalumnos g;
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
