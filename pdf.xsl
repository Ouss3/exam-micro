<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" />

    <xsl:template match="/">
        \documentclass{article}
        \usepackage{multicol} % for creating multiple columns


        \newlength{\colwidth}
        \setlength{\colwidth}{\dimexpr 0.5\textwidth - 0.5\columnsep \relax}

        \newcommand{\person}[4]{%
        \begin{minipage}{\colwidth}%
        \section*{#1} % Title
        \subsection*{#2} % Name
        \subsection*{#3} % Email (bold)
        \parbox{\linewidth}{#4} % Abstract
        \end{minipage}%
        \vspace{1cm}%
        \hrule
        \vspace{1cm}%
        }

        \begin{document}
        \begin{multicols}{2}
        <xsl:for-each select="//form">

        \person{<xsl:value-of select="title"/>}{<xsl:value-of select="firstAuthor/firstname"/>  \hspace{1mm}<xsl:value-of select="firstAuthor/lastName"/>}{<xsl:value-of select="mails/mail[1]"/> \\ <xsl:value-of select="mails/mail"/>}{<xsl:value-of select="abstracts"/>}
    </xsl:for-each>

        \end{multicols}
        \end{document}

    </xsl:template>
</xsl:stylesheet>