CREATE DATABASE Taller;
GO

USE [Taller]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[id_cliente] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[apellido] [nvarchar](100) NULL,
	[documento] [nvarchar](20) NULL,
	[tipo_documento] [nvarchar](20) NULL,
	[direccion] [nvarchar](150) NULL,
	[telefono] [nvarchar](20) NULL,
	[email] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleRepuesto]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleRepuesto](
	[id_detalle_repuesto] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NULL,
	[id_repuesto] [int] NULL,
	[cantidad] [int] NULL,
	[subtotal] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_detalle_repuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleServicio]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleServicio](
	[id_detalle_servicio] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NULL,
	[id_servicio] [int] NULL,
	[cantidad] [int] NULL,
	[subtotal] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_detalle_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empleado]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleado](
	[id_empleado] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[apellido] [nvarchar](100) NOT NULL,
	[dui] [nvarchar](20) NULL,
	[telefono] [nvarchar](20) NULL,
	[direccion] [nvarchar](150) NULL,
	[cargo] [nvarchar](50) NULL,
	[id_usuario] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MarcaVehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MarcaVehiculo](
	[id_marca] [int] IDENTITY(1,1) NOT NULL,
	[nombre_marca] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_marca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdenTrabajo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdenTrabajo](
	[id_orden] [int] IDENTITY(1,1) NOT NULL,
	[id_vehiculo] [int] NOT NULL,
	[id_cliente] [int] NOT NULL,
	[id_empleado] [int] NULL,
	[fecha_ingreso] [datetime] NULL,
	[fecha_entrega] [datetime] NULL,
	[estado] [nvarchar](20) NULL,
	[diagnostico] [nvarchar](300) NULL,
	[observaciones] [nvarchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_orden] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pago]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pago](
	[id_pago] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NOT NULL,
	[metodo_pago] [nvarchar](50) NULL,
	[monto] [decimal](10, 2) NULL,
	[descuento] [decimal](10, 2) NULL,
	[estado] [nvarchar](20) NULL,
	[fecha_pago] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_pago] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proveedor](
	[id_proveedor] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[contacto] [nvarchar](100) NULL,
	[telefono] [nvarchar](20) NULL,
	[email] [nvarchar](100) NULL,
	[direccion] [nvarchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_proveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Repuesto]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Repuesto](
	[id_repuesto] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NULL,
	[descripcion] [nvarchar](200) NULL,
	[precio] [decimal](10, 2) NULL,
	[id_proveedor] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_repuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rol]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rol](
	[id_rol] [int] IDENTITY(1,1) NOT NULL,
	[nombre_rol] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_rol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Servicio]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Servicio](
	[id_servicio] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[descripcion] [nvarchar](200) NULL,
	[precio] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoVehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoVehiculo](
	[id_tipo] [int] IDENTITY(1,1) NOT NULL,
	[nombre_tipo] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_tipo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre_usuario] [nvarchar](100) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[contrasena] [nvarchar](255) NOT NULL,
	[id_rol] [int] NOT NULL,
	[fecha_registro] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehiculo](
	[id_vehiculo] [int] IDENTITY(1,1) NOT NULL,
	[modelo] [nvarchar](50) NULL,
	[anio] [int] NULL,
	[placa] [nvarchar](20) NULL,
	[num_motor] [nvarchar](50) NULL,
	[color] [nvarchar](30) NULL,
	[kilometraje] [int] NULL,
	[fecha_ingreso] [date] NULL,
	[id_cliente] [int] NOT NULL,
	[id_tipo] [int] NOT NULL,
	[id_marca] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_vehiculo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DetalleRepuesto] ADD  DEFAULT ((1)) FOR [cantidad]
GO
ALTER TABLE [dbo].[DetalleServicio] ADD  DEFAULT ((1)) FOR [cantidad]
GO
ALTER TABLE [dbo].[OrdenTrabajo] ADD  DEFAULT (getdate()) FOR [fecha_ingreso]
GO
ALTER TABLE [dbo].[OrdenTrabajo] ADD  DEFAULT ('Pendiente') FOR [estado]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT ((0)) FOR [descuento]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT ('Pendiente') FOR [estado]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT (getdate()) FOR [fecha_pago]
GO
ALTER TABLE [dbo].[Usuario] ADD  DEFAULT (getdate()) FOR [fecha_registro]
GO
ALTER TABLE [dbo].[Vehiculo] ADD  DEFAULT (getdate()) FOR [fecha_ingreso]
GO
ALTER TABLE [dbo].[DetalleRepuesto]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[DetalleRepuesto]  WITH CHECK ADD FOREIGN KEY([id_repuesto])
REFERENCES [dbo].[Repuesto] ([id_repuesto])
GO
ALTER TABLE [dbo].[DetalleServicio]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[DetalleServicio]  WITH CHECK ADD FOREIGN KEY([id_servicio])
REFERENCES [dbo].[Servicio] ([id_servicio])
GO
ALTER TABLE [dbo].[Empleado]  WITH CHECK ADD FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_cliente])
REFERENCES [dbo].[Cliente] ([id_cliente])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_empleado])
REFERENCES [dbo].[Empleado] ([id_empleado])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_vehiculo])
REFERENCES [dbo].[Vehiculo] ([id_vehiculo])
GO
ALTER TABLE [dbo].[Pago]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[Repuesto]  WITH CHECK ADD FOREIGN KEY([id_proveedor])
REFERENCES [dbo].[Proveedor] ([id_proveedor])
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([id_rol])
REFERENCES [dbo].[Rol] ([id_rol])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_cliente])
REFERENCES [dbo].[Cliente] ([id_cliente])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_marca])
REFERENCES [dbo].[MarcaVehiculo] ([id_marca])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_tipo])
REFERENCES [dbo].[TipoVehiculo] ([id_tipo])
GO

ALTER TABLE [dbo].[Usuario]
ADD 
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Rol]
ADD [descripcion] NVARCHAR(150) NULL;
GO

ALTER TABLE [dbo].[Empleado]
ADD 
    [fecha_contratacion] DATE NULL,
    [salario] DECIMAL(10,2) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Cliente]
ADD 
    [fecha_registro] DATETIME DEFAULT(GETDATE()),
    [estado] NVARCHAR(20) DEFAULT('Activo'),
    [observaciones] NVARCHAR(300) NULL;
GO

ALTER TABLE [dbo].[Vehiculo]
ADD 
    [num_chasis] NVARCHAR(50) NULL,
    [historial_servicio_url] NVARCHAR(255) NULL,
    [estado_vehiculo] NVARCHAR(30) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[OrdenTrabajo]
ADD 
    [tiempo_estimado] DECIMAL(6,2) NULL,
    [documentos_url] NVARCHAR(255) NULL,
    [total_mano_obra] DECIMAL(10,2) DEFAULT(0),
    [total_repuestos] DECIMAL(10,2) DEFAULT(0),
    [total_general] AS ([total_mano_obra] + [total_repuestos]);
GO

ALTER TABLE [dbo].[Pago]
ADD 
    [tipo_pago] NVARCHAR(20) DEFAULT('Total'),
    [numero_factura] NVARCHAR(30) NULL,
    [observaciones] NVARCHAR(200) NULL;
GO

ALTER TABLE [dbo].[Proveedor]
ADD 
    [tipo_proveedor] NVARCHAR(50) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Servicio]
ADD 
    [categoria] NVARCHAR(50) NULL,
    [duracion_estimada] DECIMAL(6,2) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

CREATE DATABASE Taller;
GO

USE [Taller]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[id_cliente] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[apellido] [nvarchar](100) NULL,
	[documento] [nvarchar](20) NULL,
	[tipo_documento] [nvarchar](20) NULL,
	[direccion] [nvarchar](150) NULL,
	[telefono] [nvarchar](20) NULL,
	[email] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleRepuesto]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleRepuesto](
	[id_detalle_repuesto] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NULL,
	[id_repuesto] [int] NULL,
	[cantidad] [int] NULL,
	[subtotal] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_detalle_repuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleServicio]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleServicio](
	[id_detalle_servicio] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NULL,
	[id_servicio] [int] NULL,
	[cantidad] [int] NULL,
	[subtotal] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_detalle_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Empleado]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Empleado](
	[id_empleado] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[apellido] [nvarchar](100) NOT NULL,
	[dui] [nvarchar](20) NULL,
	[telefono] [nvarchar](20) NULL,
	[direccion] [nvarchar](150) NULL,
	[cargo] [nvarchar](50) NULL,
	[id_usuario] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MarcaVehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MarcaVehiculo](
	[id_marca] [int] IDENTITY(1,1) NOT NULL,
	[nombre_marca] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_marca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdenTrabajo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdenTrabajo](
	[id_orden] [int] IDENTITY(1,1) NOT NULL,
	[id_vehiculo] [int] NOT NULL,
	[id_cliente] [int] NOT NULL,
	[id_empleado] [int] NULL,
	[fecha_ingreso] [datetime] NULL,
	[fecha_entrega] [datetime] NULL,
	[estado] [nvarchar](20) NULL,
	[diagnostico] [nvarchar](300) NULL,
	[observaciones] [nvarchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_orden] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pago]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pago](
	[id_pago] [int] IDENTITY(1,1) NOT NULL,
	[id_orden] [int] NOT NULL,
	[metodo_pago] [nvarchar](50) NULL,
	[monto] [decimal](10, 2) NULL,
	[descuento] [decimal](10, 2) NULL,
	[estado] [nvarchar](20) NULL,
	[fecha_pago] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_pago] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proveedor](
	[id_proveedor] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[contacto] [nvarchar](100) NULL,
	[telefono] [nvarchar](20) NULL,
	[email] [nvarchar](100) NULL,
	[direccion] [nvarchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_proveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Repuesto]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Repuesto](
	[id_repuesto] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NULL,
	[descripcion] [nvarchar](200) NULL,
	[precio] [decimal](10, 2) NULL,
	[id_proveedor] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_repuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rol]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rol](
	[id_rol] [int] IDENTITY(1,1) NOT NULL,
	[nombre_rol] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_rol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Servicio]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Servicio](
	[id_servicio] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](100) NOT NULL,
	[descripcion] [nvarchar](200) NULL,
	[precio] [decimal](10, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_servicio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TipoVehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoVehiculo](
	[id_tipo] [int] IDENTITY(1,1) NOT NULL,
	[nombre_tipo] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_tipo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre_usuario] [nvarchar](100) NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[contrasena] [nvarchar](255) NOT NULL,
	[id_rol] [int] NOT NULL,
	[fecha_registro] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vehiculo]    Script Date: 11/6/2025 4:14:42 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehiculo](
	[id_vehiculo] [int] IDENTITY(1,1) NOT NULL,
	[modelo] [nvarchar](50) NULL,
	[anio] [int] NULL,
	[placa] [nvarchar](20) NULL,
	[num_motor] [nvarchar](50) NULL,
	[color] [nvarchar](30) NULL,
	[kilometraje] [int] NULL,
	[fecha_ingreso] [date] NULL,
	[id_cliente] [int] NOT NULL,
	[id_tipo] [int] NOT NULL,
	[id_marca] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_vehiculo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DetalleRepuesto] ADD  DEFAULT ((1)) FOR [cantidad]
GO
ALTER TABLE [dbo].[DetalleServicio] ADD  DEFAULT ((1)) FOR [cantidad]
GO
ALTER TABLE [dbo].[OrdenTrabajo] ADD  DEFAULT (getdate()) FOR [fecha_ingreso]
GO
ALTER TABLE [dbo].[OrdenTrabajo] ADD  DEFAULT ('Pendiente') FOR [estado]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT ((0)) FOR [descuento]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT ('Pendiente') FOR [estado]
GO
ALTER TABLE [dbo].[Pago] ADD  DEFAULT (getdate()) FOR [fecha_pago]
GO
ALTER TABLE [dbo].[Usuario] ADD  DEFAULT (getdate()) FOR [fecha_registro]
GO
ALTER TABLE [dbo].[Vehiculo] ADD  DEFAULT (getdate()) FOR [fecha_ingreso]
GO
ALTER TABLE [dbo].[DetalleRepuesto]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[DetalleRepuesto]  WITH CHECK ADD FOREIGN KEY([id_repuesto])
REFERENCES [dbo].[Repuesto] ([id_repuesto])
GO
ALTER TABLE [dbo].[DetalleServicio]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[DetalleServicio]  WITH CHECK ADD FOREIGN KEY([id_servicio])
REFERENCES [dbo].[Servicio] ([id_servicio])
GO
ALTER TABLE [dbo].[Empleado]  WITH CHECK ADD FOREIGN KEY([id_usuario])
REFERENCES [dbo].[Usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_cliente])
REFERENCES [dbo].[Cliente] ([id_cliente])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_empleado])
REFERENCES [dbo].[Empleado] ([id_empleado])
GO
ALTER TABLE [dbo].[OrdenTrabajo]  WITH CHECK ADD FOREIGN KEY([id_vehiculo])
REFERENCES [dbo].[Vehiculo] ([id_vehiculo])
GO
ALTER TABLE [dbo].[Pago]  WITH CHECK ADD FOREIGN KEY([id_orden])
REFERENCES [dbo].[OrdenTrabajo] ([id_orden])
GO
ALTER TABLE [dbo].[Repuesto]  WITH CHECK ADD FOREIGN KEY([id_proveedor])
REFERENCES [dbo].[Proveedor] ([id_proveedor])
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([id_rol])
REFERENCES [dbo].[Rol] ([id_rol])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_cliente])
REFERENCES [dbo].[Cliente] ([id_cliente])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_marca])
REFERENCES [dbo].[MarcaVehiculo] ([id_marca])
GO
ALTER TABLE [dbo].[Vehiculo]  WITH CHECK ADD FOREIGN KEY([id_tipo])
REFERENCES [dbo].[TipoVehiculo] ([id_tipo])
GO

ALTER TABLE [dbo].[Usuario]
ADD 
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Rol]
ADD [descripcion] NVARCHAR(150) NULL;
GO

ALTER TABLE [dbo].[Empleado]
ADD 
    [fecha_contratacion] DATE NULL,
    [salario] DECIMAL(10,2) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Cliente]
ADD 
    [fecha_registro] DATETIME DEFAULT(GETDATE()),
    [estado] NVARCHAR(20) DEFAULT('Activo'),
    [observaciones] NVARCHAR(300) NULL;
GO

ALTER TABLE [dbo].[Vehiculo]
ADD 
    [num_chasis] NVARCHAR(50) NULL,
    [historial_servicio_url] NVARCHAR(255) NULL,
    [estado_vehiculo] NVARCHAR(30) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[OrdenTrabajo]
ADD 
    [tiempo_estimado] DECIMAL(6,2) NULL,
    [documentos_url] NVARCHAR(255) NULL,
    [total_mano_obra] DECIMAL(10,2) DEFAULT(0),
    [total_repuestos] DECIMAL(10,2) DEFAULT(0),
    [total_general] AS ([total_mano_obra] + [total_repuestos]);
GO

ALTER TABLE [dbo].[Pago]
ADD 
    [tipo_pago] NVARCHAR(20) DEFAULT('Total'),
    [numero_factura] NVARCHAR(30) NULL,
    [observaciones] NVARCHAR(200) NULL;
GO

ALTER TABLE [dbo].[Proveedor]
ADD 
    [tipo_proveedor] NVARCHAR(50) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO

ALTER TABLE [dbo].[Servicio]
ADD 
    [categoria] NVARCHAR(50) NULL,
    [duracion_estimada] DECIMAL(6,2) NULL,
    [estado] NVARCHAR(20) DEFAULT('Activo');
GO