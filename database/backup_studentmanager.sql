/****** Object:  Table [dbo].[students]    Script Date: 2/2/2026 8:18:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[students](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[age] [int] NULL,
	[email] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[students] ON 

INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (1, N'Nguyễn Văn A', 20, N'a@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (2, N'Trần Thị B', 21, N'b@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (3, N'Lê Văn C', 19, N'c@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (4, N'khánh', 20, N'khanh@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (5, N'd', 22, N'd@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (6, N'kien', 21, N'kien@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (7, N'hoang', 20, N'hoang@gmail.com')
INSERT [dbo].[students] ([id], [name], [age], [email]) VALUES (8, N'a1', 12, N'min@gmail.com')
SET IDENTITY_INSERT [dbo].[students] OFF
GO
