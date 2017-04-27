<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template  match="/">
		<html>
			<head>
				<title>triMago 銘傳找飯吃最佳選擇</title>
			</head>
			<body>
				<table caption="餐廳" bgcolor="#9acd32">
					<tr>
						<th>名稱</th>
						<th>分類</th>
					</tr>
					<xsl:apply-templates/>
				</table>
				
			</body>
		</html>
	</xsl:template>
	
	<xsl:template  match="restaurant">
		<tr>
			<td><xsl:apply-templates select="@name"/></td>
			<td><xsl:apply-templates select="@typeOfMeal"/></td>
		</tr>
	</xsl:template>

	
	<xsl:template  match="@name">
		<xsl:value-of select="."/>
	</xsl:template >
	
	<xsl:template  match="@typeOfMeal">
		<xsl:value-of select="."/>
	</xsl:template >

</xsl:stylesheet>