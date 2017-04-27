<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:>
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
	
	<xsl:template match="Waterball:restaurant">
		<tr>
			<td><xsl:apply-templates select="@name"/></td>
			<td><xsl:apply-templates select="@typeOfMeal"/></td>
		</tr>
	</xsl:templaye>

	
	<xsl:template match="@name|@typeOfMeal">
		<xsl:value-of select="."/>
	</xsl:templaye>

</xsl:stylesheet>