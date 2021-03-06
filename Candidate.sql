USE [master]
GO
/****** Object:  Database [CANDIDATE]    Script Date: 02-Jul-17 7:46:19 PM ******/
CREATE DATABASE [CANDIDATE] ON  PRIMARY 
( NAME = N'CANDIDATE', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\CANDIDATE.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CANDIDATE_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\CANDIDATE_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CANDIDATE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CANDIDATE] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CANDIDATE] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CANDIDATE] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CANDIDATE] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CANDIDATE] SET ARITHABORT OFF 
GO
ALTER DATABASE [CANDIDATE] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CANDIDATE] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [CANDIDATE] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CANDIDATE] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CANDIDATE] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CANDIDATE] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CANDIDATE] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CANDIDATE] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CANDIDATE] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CANDIDATE] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CANDIDATE] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CANDIDATE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CANDIDATE] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CANDIDATE] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CANDIDATE] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CANDIDATE] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CANDIDATE] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CANDIDATE] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CANDIDATE] SET RECOVERY FULL 
GO
ALTER DATABASE [CANDIDATE] SET  MULTI_USER 
GO
ALTER DATABASE [CANDIDATE] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CANDIDATE] SET DB_CHAINING OFF 
GO
EXEC sys.sp_db_vardecimal_storage_format N'CANDIDATE', N'ON'
GO
USE [CANDIDATE]
GO
/****** Object:  StoredProcedure [dbo].[sp_listCandidateofBirthDate]    Script Date: 02-Jul-17 7:46:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_listCandidateofBirthDate] @birthDate int
	
AS
BEGIN
SET NOCOUNT ON;
SELECT * FROM Candidate
where BirthDate = @birthDate
	
END

GO
/****** Object:  Table [dbo].[Candidate]    Script Date: 02-Jul-17 7:46:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Candidate](
	[FirstName] [nvarchar](50) NULL,
	[LastName] [nvarchar](50) NULL,
	[BirthDate] [int] NULL,
	[Address] [nvarchar](50) NULL,
	[Phone] [int] NULL,
	[Email] [nchar](50) NULL,
	[Candidate_Type] [int] NULL,
	[ExpInYear] [int] NULL,
	[ProSkill] [nvarchar](50) NULL,
	[Graduation_date] [nchar](50) NULL,
	[Graduation_rank] [nvarchar](50) NULL,
	[Education] [nvarchar](50) NULL,
	[Majors] [nvarchar](50) NULL,
	[Semester] [int] NULL,
	[University_name] [nvarchar](50) NULL
) ON [PRIMARY]

GO
INSERT [dbo].[Candidate] ([FirstName], [LastName], [BirthDate], [Address], [Phone], [Email], [Candidate_Type], [ExpInYear], [ProSkill], [Graduation_date], [Graduation_rank], [Education], [Majors], [Semester], [University_name]) VALUES (N'hung', N'vo', 1995, N'hue', 975838834, N'vo@manhhung.cf                                    ', 0, 18, N'hung', NULL, NULL, NULL, NULL, 0, NULL)
INSERT [dbo].[Candidate] ([FirstName], [LastName], [BirthDate], [Address], [Phone], [Email], [Candidate_Type], [ExpInYear], [ProSkill], [Graduation_date], [Graduation_rank], [Education], [Majors], [Semester], [University_name]) VALUES (N'thieu', N'dang', 1998, N'da nagn', 933999, N'vo@asc.com                                        ', 1, 0, NULL, N'1998                                              ', N'bad', N'hce', NULL, 0, NULL)
INSERT [dbo].[Candidate] ([FirstName], [LastName], [BirthDate], [Address], [Phone], [Email], [Candidate_Type], [ExpInYear], [ProSkill], [Graduation_date], [Graduation_rank], [Education], [Majors], [Semester], [University_name]) VALUES (N'naht', N'nguyen', 1998, N'hue', 98333232, N'nghat@cs.com                                      ', 1, 0, NULL, N'27/2009                                           ', N'Excellence', N'hce', NULL, 0, NULL)
USE [master]
GO
ALTER DATABASE [CANDIDATE] SET  READ_WRITE 
GO
