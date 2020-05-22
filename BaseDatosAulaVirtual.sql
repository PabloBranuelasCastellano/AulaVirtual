create database Aula_Virtual;
use Aula_Virtual;
create table CentrosEducativos(
	CentroEducativoId int primary key not null auto_increment,
	Nombre varchar(100) not null,
	Administrador varchar(100) not null,
	Password varchar(100)not null)
	engine="Innodb" 
	default CHARSET=utf8
	Comment='En esta tabla de indican los centros Educativos,el Id del centro,
	 el nombre del Administrador y la clave de acceso';


create table Profesores(
	ProfesorId int primary key auto_increment not null,
	CentroEducativoId int not null,
	Nombre varchar(100) not null,
	PrimerApellido varchar(100)not null,
	SegundoApellido varchar(100)not null,
	NIF_NIE varchar(10)not null,
	FechaAlta date not null,
	Email varchar(100)not null,
	Usuario varchar(100)not null,
	Password varchar(100)not null,
	EsActivo Boolean not null,
	FOREIGN key(CentroEducativoId)references CentrosEducativos(CentroEducativoId)on update cascade on delete  cascade 
	)
	engine='Innodb'
	default CHARSET=utf8
	comment='En esta tabla se registran los datos de los profesores y
	 a que centro pertenecen';

create table Alumnos(
	AlumnoId int primary key not null auto_increment,
	CentroEducativoId int not null,
	Nombre varchar(100)not null,
	PrimerApellido varchar(100)not null,
	SegundoApellido varchar(100)not null,
	NIF_NIE varchar(10)not null,
	FechaAlta date not null,
	Email varchar(100)not null,
	Usuario varchar(100)not null,
	Password varchar(100)not null,
	EsActivo boolean not null,
	foreign Key(CentroEducativoId) references CentrosEducativos(CentroEducativoId) on update cascade on delete cascade
	)
	engine='Innodb'
	default Charset=utf8
	comment='En esta tabla se registran los datos de los alumnos y
	 al centro educativo que pertenecen';



create table CursosAcademicos(
	CursoAcademicoId int auto_increment primary key not null,
	CentroEducativoId int not null,
	Denominacion varchar(100)not null,
	EsActivo boolean not null,
	FechaInicio date not null,
	FechaFin date not null,
	foreign key(CentroEducativoId)references CentrosEducativos(CentroEducativoId) on update cascade on delete cascade
	)
	engine='Innodb'
	default Charset=utf8
	Comment='En esta tabla se registran los distintos cursos academicos
	 que tiene cada centro con fecha de inicio y fecha de fin';
 
create table Materias(
	MateriaId int primary key auto_increment not null,
	Nombre varchar(100)not null,
	CentroEducativoId int not null,
	EsActiva boolean not null,
	foreign key(CentroEducativoId)references CentrosEducativos(CentroEducativoId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contendrá las materias que impartirá cada centro';

create table Niveles(
	NivelId int primary key auto_increment not null,
	CentroEducativoId int not null,
	Denominacion varchar(100)not null,
	EsActivo boolean not null,
	foreign key(CentroEducativoId)references CentrosEducativos(CentroEducativoId)on update cascade on delete cascade
	
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene los diferentes niveles de estudios';


create table Grupos(
	GrupoId int primary key auto_increment not null,
	Nombre varchar(100)not null,
	CursoAcademicoId int not null,
	MateriaId int not null,
	NivelId int not null,
	ProfesorId int not null,
	foreign key(CursoAcademicoId)references CursosAcademicos(CursoAcademicoId)on update cascade on delete cascade,
	foreign key(MateriaId)references Materias(MateriaId)on update cascade on delete cascade,
	foreign key(NivelId)references Niveles(NivelId)on update cascade on delete cascade,
	foreign key(ProfesorId)references Profesores(ProfesorId)on update cascade on delete cascade 
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene el nombre del grupo ,el curso academico,
	la materia,el nivel y el profesor que la imparte';


create table GruposAlumnos(
	GrupoAlumnoId int primary key auto_increment not null,
	GrupoId int not null,
	AlumnoId int not null,
	foreign key(GrupoId)references Grupos(GrupoId)on update cascade on delete cascade,
	foreign key(AlumnoId)references Alumnos(AlumnoId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene los codigos de los alumnos y del grupo';


create table Temas(
	TemaId int primary key auto_increment not null,	
	Titulo varchar(100)not null,
	Resumen text not null,
	ProfesorId int not null,
	MateriaId int not null,
	NivelId int not null,
	FechaCreacion date not null,
	EsActivo boolean not null,
	Numero int not null,
	Orden int not null,
	foreign key(ProfesorId)references Profesores(ProfesorId)on update cascade on delete cascade,
	foreign key(MateriaId)references Materias(MateriaId)on update cascade on delete cascade ,
	foreign key(NivelId)references Niveles(NivelId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene el nombre,resumen numero de tema ,
	la materia a la que corresponde,el profesor que la imparte ,
	el nivel,cuando se creo y el orden el que se tiene que mostrar
	cada tema';
	
create table Cuestionarios(
	CuestionarioId int primary key auto_increment not null,			
	ProfesorId int not null,
	Titulo varchar(100)not null,
	Instrucciones text not null,
	Resumen text not null,
	NumeroPreguntasPorTest int not null,
	PuntosAcierto int not null,
	PuntosError int not null,
	FechaCreacion date not null,
	EsActivo boolean not null,
	foreign key(ProfesorId)references Profesores(ProfesorId)on update cascade on delete cascade 
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene las intrucciones ,el numero de preguntas,
	el numero de aciertos y fallos y cuando se creo';
	
create table CuestionariosGrupos(
	CuestionarioGrupoId int primary key auto_increment not null,
	CuestionarioId int not null,
	GrupoId int not null,
	FechaInicio date not null,
	FechaFin date not null,
	foreign key(CuestionarioId)references Cuestionarios(CuestionarioId)on update cascade on delete cascade,
	foreign key(GrupoId)references Grupos(GrupoId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene el identificador del cuestioanario,
	que grupo lo ha realizado ,cuando se inició y cuando se termino';


create table Puntos(
	PuntoId int primary key auto_increment not null,
	TemaId int not null,
	Titulo varchar(100)not null,
	Resumen text not null,
	Texto text not null,
	Orden int not null,
	Numero int not null,
	EsActivo boolean not null,
	foreign key(TemaId)references Temas(TemaId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene los apartados que tiene cada tema ,
	numero y orden en que se va a presentar';
	
create table Intentos(
	IntentoId int primary key auto_increment not null,
	AlumnoId int not null,
	CuestionarioId int not null,
	FechaComienzo date not null,
	FechaFin date not null,
	TiempoTotal int not null,
	Puntuacion int not null,
	Finalizado boolean not null,
	foreign key(AlumnoId)references Alumnos(AlumnoId)on update cascade on delete cascade,
	foreign key(CuestionarioId)references Cuestionarios(CuestionarioId)on update cascade on delete cascade 
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene el numero de veces que los alumnos han intentado los 
	cuestionarios,cuando han empezado ,cuando lo han terminado ,los puntos obtenidos 
	y el tiempo que han tardado';
	

create table Preguntas(
	PreguntaId int primary key auto_increment not null,
	CuestionarioId int not null,
	Texto text not null,
	FechaCreacion date not null,
	Orden int not null,
	EsActivo boolean not null,
	foreign key(CuestionarioId)references Cuestionarios(CuestionarioId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene las preguntas,a  que formulario pertenecen,
	el orden en que se muestran y cuando se crearon';
	
create table Opciones(
	OpcionId int primary key auto_increment not null,
	PreguntaId int not null,
	Texto text not null,
	EsCorrecta boolean not null,
	FechaCreacion date not null,
	Orden int not null,
	EsActivo boolean not null,
	foreign key(PreguntaId)references Preguntas(PreguntaId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla contiene las opciones de las distintas preguntas ,
	cuando se crearon ,en que orden se muestran y
	si es correcta o no';
	
create table OpcionesPuntos(
	OpcionPuntoId int primary key auto_increment not null,
	OpcionId int not null,
	PuntoId int not null,
	foreign key(OpcionId)references Opciones(OpcionId)on update cascade on delete cascade,
	foreign key(PuntoId)references Puntos(PuntoId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla guarda la respuesta elegida
	 y a que punto corresponde';
	 
create table RespuestasAlumno(
	RespuestaAlumnoId int primary key auto_increment not null,
	IntentoId int not null,
	PreguntaId int not null,
	OpcionId int not null,
	Tiempo int not null,
	foreign key(IntentoId)references Intentos(IntentoId)on update cascade on delete cascade,
	foreign key(PreguntaId)references Preguntas(PreguntaId)on update cascade on delete cascade,
	foreign key(OpcionId)references Opciones(OpcionId)on update cascade on delete cascade
	)
	engine='Innodb'
	default charset=utf8
	comment='Esta tabla guarda los intentos ,las preguntas ,las respuestas
	y el tiempo que ha tardado en hacerlas el alumno ';