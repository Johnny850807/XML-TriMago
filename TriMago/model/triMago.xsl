<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
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
				<xsl:for-each select="Waterball:WebSite/Waterball:restaurant">
					<tr>
						<td><xsl:value-of select="@name"/></td>
						<td><xsl:value-of select="@typeOfMeal"/></td>
					</tr>
				</xsl:for-each>
			</table>
		</body>
	</html>
	</xsl:template>

</xsl:stylesheet>