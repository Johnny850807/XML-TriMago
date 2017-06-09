<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
	<xsl:template  match="/">
		<html>
			<head>
				<title>銘傳找飯吃? TriMaGo</title>
				<link rel="shortcut icon"
					href="http://imgur.com/download/NCCXqIj" />
				<meta charset="utf-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1"/>
				<meta name="description" content="銘傳找飯吃? TriMago! 一個無須登入可自由分享以及評價的銘傳周邊美食網站，歡迎分享你的美食經驗！"/>
				<meta name="keywords" content="銘傳大學,美食,trimago"/>
				<meta name="author" content="潘冠辰"/>
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
				<link rel="stylesheet" href="triMago.css"/>
				<link rel="stylesheet" href="detail.css"/>

				<link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css" />
				<script src="js/star-rating.js" type="text/javascript"></script>
				<script>
				  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
				  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
				  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
				  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

				  ga('create', 'UA-100403751-1', 'auto');
				  ga('send', 'pageview');

				</script>
        
			</head>
			<body>
					<xsl:apply-templates select="Waterball:WebSite"/>
			</body>
      
		</html>
	</xsl:template>
  
  <xsl:template match="Waterball:WebSite">
      
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130" id="nav">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index" id="logo">
                    <img src="https://imgur.com/download/8WZa7jf" alt="logo"/>
                </a>
            </div>
            <nav class="nav nav-pills" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index" id="homepage" class="navigationRight" > 首頁</a></li>
                    <li><a href="about" id="about" class="navigationRight" > 關於我們</a></li>
                </ul>
            </nav>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-9 text-left"  id="leftSelection">
				
              <div class="add-comment">
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myCommentModal">
                  我要留言
                </button>
				<link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css" />
				<script src="js/star-rating.js" type="text/javascript"></script>
              </div>
			  <div class="modal fade" id="myCommentModal" role="dialog">
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<h4 class="modal-title">留言</h4>
					  </div>
					  <form  id="modal-form-comment" method="post" action="detail/create" >
						<input type="hidden" name="id"  value="{//Waterball:restaurant/@id}" />
						<div class="modal-body">
						  <div class="form-group">
							<label for="res-name">暱稱:</label>
							<input type="text" class="form-control" id="res-name" name="name" required="required"/>
						  </div> 
						  <div class="form-group">
							<label for="input-1" class="control-label">給予評價:</label>
							<input id="input-1" name="rate" class="rating rating-loading" data-min="0" data-max="5" data-step="1"/>	
						  </div> 
						  
						  <div class="form-group">
							<label for="content">內容:</label>
							<textarea class="form-control" rows="5" id="content" required="required" name="content"></textarea>
						  </div> 
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						  <button type="submit" class="btn btn-default" form="modal-form-comment" >新增</button>
						</div>
					  </form>
					</div>
				  </div>
        </div>
                <xsl:apply-templates select="Waterball:restaurant"/>
            </div>
            <div class="col-sm-3 sidenav">
                <div class="well">
					<h2><kbd>2017/6/1 2:31</kbd></h2> 
					<h2><kbd>TeamPathy</kbd></h2> 
					<h2 style="display:inline;"><kbd>專研APP強勢上架</kbd></h2>
                    <img src="adv1.png" style="width:40px;height:40px;"/>
					<h3><kbd>盡請期待 另外徵求APP高手</kbd></h3>
                </div>
                <div class="well">
					<div class="alert alert-warning">
						<p><strong>請各位不要留表情唷!!!</strong></p>
					</div>
                </div>
				<div class="well">
                    <div class="alert alert-warning">
						<p><strong>請各位不要留表情唷!!!</strong></p>
					</div>
                </div>
				<div class="well">
                    <div class="alert alert-warning">
						<p><strong>Please don't leave an emoji!!!</strong></p>
					</div>
                </div>
            </div>
        </div>
    </div>

    <footer class="container-fluid text-center">
        <p>WaterBall @Copy MingChang Homework-Purpose</p>
    </footer>
  
  
	 
  </xsl:template>
	
	<xsl:template  match="Waterball:restaurant">
		<script>
				$(document).ready(function(){
					
					$('#toMapButton').click(function(e) {
						$.ajax({ type: "GET",   
							 url: 'map?searchInput=<xsl:value-of select="@name"/>',   
							 success : function(text)
							 {
								 $('#leftSelection').html($(text).find('#leftSelection').html());
							 }
						});
					});
					
				});
				
			</script>
    <div class="media" id="restaurantList">
	<div class="col-sm-3">
        <div class="media-left">
          <xsl:variable name="imageUrl" select="@imageUrl"/>
          <img src="{$imageUrl}" class="media-object"/>
        </div>
	</div>
	<div class="col-sm-9">
		<div class="media-body">
       <h1 class="media-heading"> <xsl:value-of select="@name"/> </h1>
       <div class="item__stars-wrp">
         <!--計算該餐廳的評價 = 所有留言給予的評價之平均-->
         <xsl:variable name="rate" select="sum(Waterball:comment/@rate) div count(Waterball:comment)"/>
         <xsl:for-each select="(//node())[$rate >= position()]">
           <span class="icon-ic icon-category/star item__star"><svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 12 12"><path class="svg-color--primary" fill="#F6AB3F" d="M11.988 5.21c-.052-.275-.27-.488-.545-.534l-3.604-.6L6.63.455C6.542.184 6.287 0 6 0s-.542.184-.632.456L4.16 4.076l-3.603.6c-.275.046-.493.26-.545.533-.052.273.072.55.312.695L3.2 7.63l-1.165 3.493c-.093.28.01.59.25.758.115.08.25.12.382.12.148 0 .295-.05.416-.146L6 9.52l2.917 2.333c.12.098.27.147.416.147.133 0 .267-.04.38-.12.244-.17.346-.478.252-.758L8.8 7.63l2.876-1.725c.24-.144.364-.422.312-.696z"></path></svg></span>
         </xsl:for-each>
       </div>
       <p class="address"><xsl:value-of select="@address"/></p>
	   <p class="typeOfMeal"><xsl:value-of select="@typeOfMeal"/></p>
       <p class="price">價位 <xsl:value-of select="@price"/></p>
	   <xsl:variable name="name" select="@name"/>
		<input type="submit" value="前往地圖" class="btn btn-success detailsButton" id="toMapButton"/>
     </div>
	 </div>
    </div>
			<xsl:apply-templates select="Waterball:comment"/>
	</xsl:template>

  <xsl:template  match="Waterball:comment">
    <div class="media" id="commentList">
      <div class="col-sm-3">
        <div class="media-left">
          <p id="comment-name">
            <xsl:value-of select="Waterball:name"/>
          </p>
          <xsl:variable name="rate" select="@rate"/>
          <xsl:for-each select="(//node())[$rate >= position()]">
            <span class="icon-ic icon-category/star item__star">
              <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 12 12">
                <path class="svg-color--primary" fill="#F6AB3F" d="M11.988 5.21c-.052-.275-.27-.488-.545-.534l-3.604-.6L6.63.455C6.542.184 6.287 0 6 0s-.542.184-.632.456L4.16 4.076l-3.603.6c-.275.046-.493.26-.545.533-.052.273.072.55.312.695L3.2 7.63l-1.165 3.493c-.093.28.01.59.25.758.115.08.25.12.382.12.148 0 .295-.05.416-.146L6 9.52l2.917 2.333c.12.098.27.147.416.147.133 0 .267-.04.38-.12.244-.17.346-.478.252-.758L8.8 7.63l2.876-1.725c.24-.144.364-.422.312-.696z"></path>
              </svg>
            </span>
          </xsl:for-each>
        </div>

      </div>
      <div class="col-sm-9" >
        <div class="media-body">
          <div class="media-heading">
            <p>
              <pre><xsl:value-of select="Waterball:content"/></pre>
            </p>
            <p id="date">
              <xsl:value-of select="Waterball:date"/>
            </p>
          </div>
        </div>   
      </div>
    </div>
  </xsl:template>
  

</xsl:stylesheet>