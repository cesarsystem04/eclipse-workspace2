<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:strip-space elements="*" />
	<xsl:template match="*">
		<xsl:element name="ns0:{local-name()}"
			namespace="http://www.sat.gob.mx/cfd/4">
			<xsl:apply-templates select="@* | node()" />
		</xsl:element>
	</xsl:template>
	<xsl:template match="@*">
		<xsl:attribute name="{local-name()}">
		<xsl:value-of select="." />
		</xsl:attribute>
	</xsl:template>
</xsl:stylesheet>