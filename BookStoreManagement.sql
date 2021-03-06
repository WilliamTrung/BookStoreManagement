USE [master]
GO
/****** Object:  Database [BookStoreManagement]    Script Date: 9/8/2021 1:07:08 PM ******/
CREATE DATABASE [BookStoreManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStoreManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.FPTU\MSSQL\DATA\BookStoreManagement.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BookStoreManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.FPTU\MSSQL\DATA\BookStoreManagement_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [BookStoreManagement] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStoreManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStoreManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStoreManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStoreManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStoreManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStoreManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStoreManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookStoreManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStoreManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStoreManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStoreManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStoreManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStoreManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStoreManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStoreManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStoreManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookStoreManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStoreManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStoreManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStoreManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStoreManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStoreManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStoreManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStoreManagement] SET RECOVERY FULL 
GO
ALTER DATABASE [BookStoreManagement] SET  MULTI_USER 
GO
ALTER DATABASE [BookStoreManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStoreManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStoreManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStoreManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BookStoreManagement] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BookStoreManagement] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookStoreManagement', N'ON'
GO
ALTER DATABASE [BookStoreManagement] SET QUERY_STORE = OFF
GO
USE [BookStoreManagement]
GO
/****** Object:  Table [dbo].[tblCatagories]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCatagories](
	[catagoryID] [nvarchar](50) NOT NULL,
	[catagoryName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[catagoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[detailID] [int] IDENTITY(0,1) NOT NULL,
	[orderID] [int] NULL,
	[productID] [nvarchar](50) NULL,
	[quantity] [numeric](10, 0) NOT NULL,
	[price] [numeric](18, 0) NOT NULL,
	[statusID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [int] IDENTITY(0,1) NOT NULL,
	[userID] [int] NULL,
	[orderDate] [date] NOT NULL,
	[orderTotal] [numeric](16, 0) NOT NULL,
	[statusID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProducts]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProducts](
	[productID] [nvarchar](50) NOT NULL,
	[productPrice] [numeric](16, 0) NOT NULL,
	[productName] [nvarchar](50) NOT NULL,
	[productImage] [nvarchar](50) NOT NULL,
	[catagoryID] [nvarchar](50) NULL,
	[quantity] [numeric](10, 0) NOT NULL,
	[statusID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [nvarchar](50) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 9/8/2021 1:07:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [int] IDENTITY(0,1) NOT NULL,
	[userName] [nvarchar](50) NOT NULL,
	[userBirthday] [date] NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[roleID] [nvarchar](50) NULL,
	[statusID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCatagories] ([catagoryID], [catagoryName]) VALUES (N'act', N'Action')
INSERT [dbo].[tblCatagories] ([catagoryID], [catagoryName]) VALUES (N'fts', N'Fantasy')
INSERT [dbo].[tblCatagories] ([catagoryID], [catagoryName]) VALUES (N'nov', N'Novel')
INSERT [dbo].[tblCatagories] ([catagoryID], [catagoryName]) VALUES (N'rom', N'Romance')
GO
SET IDENTITY_INSERT [dbo].[tblOrderDetails] ON 

INSERT [dbo].[tblOrderDetails] ([detailID], [orderID], [productID], [quantity], [price], [statusID]) VALUES (1, 3, N'HP1', CAST(5 AS Numeric(10, 0)), CAST(150000 AS Numeric(18, 0)), 0)
SET IDENTITY_INSERT [dbo].[tblOrderDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[tblOrders] ON 

INSERT [dbo].[tblOrders] ([orderID], [userID], [orderDate], [orderTotal], [statusID]) VALUES (0, 1, CAST(N'2021-06-17' AS Date), CAST(100000 AS Numeric(16, 0)), 0)
INSERT [dbo].[tblOrders] ([orderID], [userID], [orderDate], [orderTotal], [statusID]) VALUES (1, 2, CAST(N'2021-06-17' AS Date), CAST(100000 AS Numeric(16, 0)), 0)
INSERT [dbo].[tblOrders] ([orderID], [userID], [orderDate], [orderTotal], [statusID]) VALUES (2, 3, CAST(N'2021-06-17' AS Date), CAST(100000 AS Numeric(16, 0)), 0)
INSERT [dbo].[tblOrders] ([orderID], [userID], [orderDate], [orderTotal], [statusID]) VALUES (3, 3, CAST(N'2021-09-08' AS Date), CAST(750000 AS Numeric(16, 0)), 0)
INSERT [dbo].[tblOrders] ([orderID], [userID], [orderDate], [orderTotal], [statusID]) VALUES (4, 3, CAST(N'2021-09-08' AS Date), CAST(0 AS Numeric(16, 0)), 1)
SET IDENTITY_INSERT [dbo].[tblOrders] OFF
GO
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HD2018', CAST(49000 AS Numeric(16, 0)), N'Ha Do 2018', N'hd2018.jpg', N'nov', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP1', CAST(152000 AS Numeric(16, 0)), N'Harry Potter vol1', N'harrypottervol1.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP2', CAST(150000 AS Numeric(16, 0)), N'Harry Potter vol2', N'harrypottervol2.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP3', CAST(150000 AS Numeric(16, 0)), N'Harry Potter vol3', N'harrypottervol3.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP4', CAST(150000 AS Numeric(16, 0)), N'Harry Potter vol4', N'harrypottervol4.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP5', CAST(150000 AS Numeric(16, 0)), N'Harry Potter vol5', N'harrypottervol5.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HP6', CAST(150000 AS Numeric(16, 0)), N'Harry Potter vol6', N'harrypottervol6.jpg', N'fts', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'HVCX', CAST(100000 AS Numeric(16, 0)), N'Toi thay hoa vang tren co xanh', N'hvcx.jpg', N'nov', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'LNTL2017', CAST(69000 AS Numeric(16, 0)), N'La nam trong la tai ban 2017', N'lntl2017.jpg', N'nov', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'NGK2020', CAST(67000 AS Numeric(16, 0)), N'Nha gia kim tai ban 2020', N'ngk2020.jpg', N'nov', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'OD', CAST(253000 AS Numeric(16, 0)), N'One Day', N'oneday.jpg', N'rom', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'SH', CAST(172000 AS Numeric(16, 0)), N'Sky Hunter', N'sh.jpg', N'act', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'TFIOS', CAST(253000 AS Numeric(16, 0)), N'The fault in our stars', N'TFIOS.jpg', N'rom', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'THRoR', CAST(535000 AS Numeric(16, 0)), N'The hobbit and The lord of the rings', N'hobbit.jpg', N'act', CAST(5 AS Numeric(10, 0)), 1)
INSERT [dbo].[tblProducts] ([productID], [productPrice], [productName], [productImage], [catagoryID], [quantity], [statusID]) VALUES (N'WMID', CAST(253000 AS Numeric(16, 0)), N'We met in December', N'WMID.jpg', N'rom', CAST(5 AS Numeric(10, 0)), 1)
GO
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'ad', N'ADMIN')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'us', N'USER')
GO
INSERT [dbo].[tblStatus] ([statusID], [statusName]) VALUES (0, N'Deactive')
INSERT [dbo].[tblStatus] ([statusID], [statusName]) VALUES (1, N'Active')
GO
SET IDENTITY_INSERT [dbo].[tblUsers] ON 

INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (0, N'admin123456', CAST(N'2001-11-12' AS Date), N'123', N'ad', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (1, N'sa', CAST(N'2001-11-12' AS Date), N'123', N'ad', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (2, N'Kimura Mai', CAST(N'2001-11-12' AS Date), N'123', N'us', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (3, N'Satou', CAST(N'2002-11-12' AS Date), N'123', N'us', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (4, N'Daniel', CAST(N'2003-11-12' AS Date), N'123', N'us', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (5, N'toilaadmin', CAST(N'2001-11-12' AS Date), N'123', N'ad', 1)
INSERT [dbo].[tblUsers] ([userID], [userName], [userBirthday], [password], [roleID], [statusID]) VALUES (7, N'test', CAST(N'2005-11-12' AS Date), N'123', N'ad', 1)
SET IDENTITY_INSERT [dbo].[tblUsers] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tblUsers__66DCF95C25309C18]    Script Date: 9/8/2021 1:07:08 PM ******/
ALTER TABLE [dbo].[tblUsers] ADD UNIQUE NONCLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[tblProducts] ([productID])
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD FOREIGN KEY([catagoryID])
REFERENCES [dbo].[tblCatagories] ([catagoryID])
GO
ALTER TABLE [dbo].[tblProducts]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
USE [master]
GO
ALTER DATABASE [BookStoreManagement] SET  READ_WRITE 
GO
