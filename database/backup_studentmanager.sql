SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[students](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[age] [int] NULL,
	[email] [nvarchar](255) NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[students] ADD PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO


INSERT INTO students(name, email, age) VALUES (N'Trần Thị B','b@gmail.com',21);
INSERT INTO students(name, email, age) VALUES (N'Lê Văn C','c@gmail.com',19);
INSERT INTO students(name, email, age) VALUES (N'khánh','khanh@gmail.com',20);
INSERT INTO students(name, email, age) VALUES (N'd','d@gmail.com',22);
INSERT INTO students(name, email, age) VALUES (N'kien','kien@gmail.com',21);
INSERT INTO students(name, email, age) VALUES (N'hoang','hoang@gmail.com',20);
INSERT INTO students(name, email, age) VALUES (N'a1','min@gmail.com',12);