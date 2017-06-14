<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
  	<xsl:param name="sort"/>
  <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
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
				<link rel="canonical" href="http://www.teampathy.tk:8080/TriMago" />
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
				<script src="https://apis.google.com/js/platform.js" async="true" defer="true">
				  {lang: 'zh-TW'}
				</script>
				<link rel="stylesheet" href="triMago.css"/>
				<link rel="stylesheet" href="detail.css"/>
        <script language = "JavaScript"  type="text/javascript" >
			
	
          $(function(){
          $(".modal-dropdown-menu-create").click(function(){
          $("#modal-select-btn:first-child").text($(this).text());
          $("#modal-select-btn:first-child").val($(this).text());
          $("#modal-typeOfMeal-input:first-child").val($(this).text());
          });
          });

          function onSubmitValidate(){
            return true;
          }
		  
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

		  ga('create', 'UA-100403751-1', 'auto');
		  ga('send', 'pageview');
        </script>
		
		<style>  <!--For the Google Custom Search Issue-->
			.cse .gsc-search-button input.gsc-search-button-v2,
			input.gsc-search-button-v2 {
				height: 26px !important;
				margin-top: 0 !important;
				min-width: 13px !important;
				padding: 5px 26px !important;
				width: 68px !important;
			}
			kbd{
				font-size:22px;
			}
		</style>
		
        <div class="modal fade" id="myModal" role="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">新增餐廳</h4>
              </div>
              <form  id="modal-form" method="post" action="index/create"   enctype="multipart/form-data" >
                <input type="hidden" name="typeOfMeal" id="modal-typeOfMeal-input" />
                <div class="modal-body">
                  <div class="form-group">
                    <label for="res-name">餐廳名稱:</label>
                    <input type="text" class="form-control" id="res-name" name="name" required="required"/>
                  </div>
                  <div class="form-group">
                    <label for="res-img">餐廳照片:</label>
                    <input type="file" class="form-control" id="res-img" name="image" accept="image/*" required="required"/>
                  </div>
                  <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" id="modal-select-btn" type="button" data-toggle="dropdown" >
                      選擇分類
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu modal-dropdown-menu">
                      <li><a href="#" class="modal-dropdown-menu-create">無分類</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">早午餐店</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">小吃店、路邊攤</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">甜點、冰</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">正餐、麵、飯</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">下午茶、咖啡廳</a></li>
						<li><a href="#" class="modal-dropdown-menu-create">速食店、漢堡店</a></li>
                    </ul>
                    
                  </div>
                  <div class="form-group">
                    <label for="res-address">請輸入真實詳細地址:</label>
                    <input type="text" class="form-control" id="res-address" name="address" required="required"/>
                  </div>
                  <div class="form-group">
                    <label for="res-avgprice">平均價格:</label>
                    <input type="number" class="form-control" id="res-avgprice" name="price" required="required"/>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  <button type="submit" class="btn btn-default" form="modal-form" >新增</button>
                </div>
              </form>
            </div>
          </div>
        </div>
			</head>
			<body>
				<script>
					$body = $("body");
					$(document).on({
						ajaxStart: function() { $body.addClass("loading");    },
						 ajaxStop: function() { $body.removeClass("loading"); }    
					});
				</script>
				<xsl:apply-templates select="Waterball:WebSite"/>
				<div class="modal-ajax"></div> 
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
                    <li><a href="#" id="homepage" class="navigationRight" > 首頁</a></li>
                    <li><a href="about" id="about" class="navigationRight" > 關於我們</a></li>
                </ul>
            </nav>
        </div>
    </nav>
	
    <main class="container-fluid">
        <div class="row content">
            <div class="col-sm-9 text-left" id="leftSelection">
				<script>
				$(document).ready(function(){
					$('#homepage').click(function(e) {
						$.ajax({ type: "GET",   
							 url: 'index',   
							 success : function(text)
							 {
								 $('#leftSelection').html($(text).find('#leftSelection').html());
							 }
						});
					});
					$('#searchPanelForm').on('submit', function(e){
						e.preventDefault();
						var input = $('#searchInput').val();
						var type = $('#type-menu-input').val();
						var sort = $('#sort-menu-input').val();
						var eventUrl = 'index?searchInput='+input+'&amp;typeOfMeal='+type+'&amp;sort='+sort;

						$.ajax({ type: "GET",   
							 url: eventUrl,   
							 success : function(text)
							 {
								 $('#leftSelection').html($(text).find('#leftSelection').html());
							 }
						});
					});
					$("#map-button").click(function(){
					$.ajax({ type: "GET",   
							 url: 'map',   
							 success : function(text)
							 {
								 $('#leftSelection').html($(text).find('#leftSelection').html());
							 }
						});
					});
				});
				
			</script>
              <form action="index" method="get" id="searchPanelForm">
				<input name="typeOfMeal" id="type-menu-input" type="hidden" />
				<input name="sort" id="sort-menu-input" type="hidden" />
                <div class="container" id="searchPanel">
                    <div class="row" id="searchBar">
                        <input class="flipkart-navbar-input col-xs-11" placeholder="輸入想查詢的餐廳名稱" name="searchInput" id="searchInput"/>
                        <button class="flipkart-navbar-button col-xs-1" style="text-align:center;">
                            <svg width="15px" height="15px" >
                                <path d="M11.618 9.897l4.224 4.212c.092.09.1.23.02.312l-1.464 1.46c-.08.08-.222.072-.314-.02L9.868 11.66M6.486 10.9c-2.42 0-4.38-1.955-4.38-4.367 0-2.413 1.96-4.37 4.38-4.37s4.38 1.957 4.38 4.37c0 2.412-1.96 4.368-4.38 4.368m0-10.834C2.904.066 0 2.96 0 6.533 0 10.105 2.904 13 6.486 13s6.487-2.895 6.487-6.467c0-3.572-2.905-6.467-6.487-6.467 "></path>
                            </svg>
                        </button>
                    </div>
                    <div class="row" id="mealTypeMenu" >
                          <!--讓點擊分類之後能夠將文字印到按鈕上-->
                          <script>
                            $(function(){

                            $(".type-item").click(function(){

                            $("#type-menu-btn").text($(this).text());
                            $("#type-menu-btn").val($(this).text());
							$("#type-menu-input").val($(this).text());
                            });
							
							$(".sort-item").click(function(){

                            $("#sort-btn").text($(this).text());
                            $("#sort-btn").val($(this).text());
							$("#sort-menu-input").val($(this).text());
                            });

                            });
                          </script>
							
                            <div class="dropdown" style="margin-top:8px;display:inline;">
                                <input value ="選擇搜尋分類" name="typeOfMeal" id="type-menu-btn" class="btn btn-primary dropdown-toggle search-btn"  type="button" data-toggle="dropdown"/>
        
                                <ul class="dropdown-menu search-dropdown-menu">
                                    <li><a href="#" class="type-item">無分類</a></li>
									<li><a href="#" class="type-item">早午餐店</a></li>
									<li><a href="#" class="type-item">小吃店、路邊攤</a></li>
									<li><a href="#" class="type-item">甜點、冰</a></li>
									<li><a href="#" class="type-item">正餐、麵、飯</a></li>
									<li><a href="#" class="type-item">下午茶、咖啡廳</a></li>
									<li><a href="#" class="type-item">速食店、漢堡店</a></li>
                                </ul> 
                            </div>
							
							<div class="dropdown" style="margin-top:8px;display:inline;">
                                <input value ="選擇結果排序" name="sort" id="sort-btn" class="btn btn-success dropdown-toggle search-btn"  type="button" data-toggle="dropdown"/>
        
                                <ul class="dropdown-menu search-dropdown-menu">
                                    <li><a href="#" class="sort-item">無排序</a></li>
                                    <li><a href="#" class="sort-item">便宜到貴</a></li>
                                    <li><a href="#" class="sort-item">貴到便宜</a></li>
									<li><a href="#" class="sort-item">評價低到高</a></li>
									<li><a href="#" class="sort-item">評價高到低</a></li>
									<li><a href="#" class="sort-item">留言數低到高</a></li>
									<li><a href="#" class="sort-item">留言數高到低</a></li>
                                </ul>
                            </div>
							
							<button type="button" class="btn btn-info search-btn" data-toggle="modal"  
                                    data-target="#myModal">
                              <span class="glyphicon glyphicon-plus"></span> 我要推薦餐廳
                            </button>
							
							<button type="button"  
								class="btn btn-danger search-btn" id="map-button">美食地圖 <span class="glyphicon glyphicon-hand-right"></span></button>
                    </div>
                </div>
              </form>
				<div class="g-plusone" data-annotation="inline" data-width="300"></div>
                <hr/>
					<xsl:choose>
					<xsl:when test="$sort='便宜到貴'">
						<xsl:apply-templates select="Waterball:restaurant">
							<xsl:sort order="ascending" select="@price" data-type="number"/>			
						</xsl:apply-templates>
					</xsl:when>
					<xsl:when test="$sort='貴到便宜'">
						<xsl:apply-templates select="Waterball:restaurant">
							<xsl:sort order="descending" select="@price" data-type="number"/>			
						</xsl:apply-templates>
					</xsl:when>
					  <xsl:when test="$sort='評價低到高'">
						<xsl:apply-templates select="Waterball:restaurant">
					<xsl:sort order="ascending" select="sum(Waterball:comment/@rate) div count(Waterball:comment)" data-type="number"/>			
				</xsl:apply-templates>
					  </xsl:when>
					  <xsl:when test="$sort='評價高到低'">
						<xsl:apply-templates select="Waterball:restaurant">
					<xsl:sort order="descending" select="sum(Waterball:comment/@rate) div count(Waterball:comment)" data-type="number"/>			
				</xsl:apply-templates>
					  </xsl:when>
					  <xsl:when test="$sort='留言數低到高'">
						<xsl:apply-templates select="Waterball:restaurant">
					<xsl:sort order="ascending" select="count(Waterball:comment)" data-type="number"/>			
				</xsl:apply-templates>
					  </xsl:when>
					  <xsl:when test="$sort='留言數高到低'">
						<xsl:apply-templates select="Waterball:restaurant">
					<xsl:sort order="descending" select="count(Waterball:comment)" data-type="number"/>			
				</xsl:apply-templates>
					  </xsl:when>
					  <xsl:otherwise>
						<xsl:apply-templates select="Waterball:restaurant"></xsl:apply-templates>
					  </xsl:otherwise>
					</xsl:choose>
				
            </div>
            <div class="col-sm-3 sidenav">
				<script>
				  (function() {
					var cx = '016452915066670004559:xkj40c_fjrw';
					var gcse = document.createElement('script');
					gcse.type = 'text/javascript';
					gcse.async = true;
					gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
					var s = document.getElementsByTagName('script')[0];
					s.parentNode.insertBefore(gcse, s);
				  })(); 
				</script>
				<div class="gcse-search"></div>
                <div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top:20px;margin-bottom:20px;">
						<div class="carousel-inner">
							<xsl:apply-templates select="Waterball:restaurant/@imageUrl" />
						</div>
						<a class="left carousel-control" href="#myCarousel" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"></span>
							<span class="sr-only">Next</span>
						</a>
				</div>
                <div class="well">
                    <audio src="music3.mp3" controls="controls" style="width:100%;"></audio>
                </div>
				<div class="well">
					<h2><kbd>2017/6/1 2:31</kbd></h2> 
					<h2><kbd>TeamPathy</kbd></h2> 
					<h2 style="display:inline;"><kbd>專研APP強勢上架</kbd></h2>
                    <img src="adv1.png" style="width:40px;height:40px;"/>
					<iframe src="teampathy.mp4" style="width:100%;height:200px;margin-top:15px;margin-bottom:15px;" allowfullscreen=""></iframe>
					<h3><kbd>盡請期待 另外徵求APP高手</kbd></h3>
                </div>
                <div class="well">
					<h2><kbd>2017/6/2 2:40</kbd></h2> 
					<h2><kbd>Instagram Spy</kbd></h2> 
					<h2><kbd>To Show And Dominate Followers</kbd></h2>
					<h2 style="display:inline;"><kbd>Coming Soon.</kbd></h2>
					<iframe src="IG分析.mp4" style="width:100%;height:200px;margin-top:15px;margin-bottom:15px;" allowfullscreen=""></iframe>
					<h3><kbd>To Unfollow Them..</kbd></h3>
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
				<div class="well">
					<h2><kbd>驚世語錄</kbd></h2>
                    <img src="adv2.png"/>
                </div>
				<div class="well">
					<div class="alert alert-danger">
						<p><strong>我是 七點 還是 九點 ?</strong></p>
					</div>
                </div>
            </div>
        </div>
    </main>

    <footer class="container-fluid text-center">
        <p>WaterBall @Copy MingChang Homework-Purpose</p>
    </footer>
  
  </xsl:template>
  
  <xsl:template match="Waterball:restaurant/@imageUrl">
		<xsl:if test="position() > 1">
				<div class="item">
						<a href="detail?id={../@id}"><img src="{../@imageUrl}" alt="{../@name}" style="width:100%;"/></a>
						<div class="carousel-caption">
						  <h2><xsl:value-of select="../@name"/></h2>
						</div>
				</div>
		</xsl:if>
		<xsl:if test="position() = 1">
				<div class="item active">
						<a href="detail?id={../@id}"><img src="{../@imageUrl}" alt="{../@name}" style="width:100%;"/></a>
						<div class="carousel-caption">
						  <h2><xsl:value-of select="../@name"/></h2>
						</div>
				</div>
		</xsl:if>
  </xsl:template>
  
	<xsl:template  match="Waterball:restaurant">
		<script>
			$(document).ready(function() {
				$("#detailsButton<xsl:value-of select="@id"/>").click(function(){
					$.ajax({ type: "GET",   
							 url: 'detail?id=<xsl:value-of select="@id"/>',   
							 success : function(text)
							 {
								 $('#leftSelection').html($(text).find('#leftSelection').html());
								 window.scrollTo(0,0);
							 }
					});
				}); 
			});
		</script>
		<xsl:variable name="id" select="@id"/>	
    <div class="media" id="restaurantList">
	<div class="col-sm-3">
        <div class="media-left">
          <xsl:variable name="imageUrl" select="@imageUrl"/>
          <img src="{$imageUrl}" alt="{@name}" class="media-object" />
        </div>
	</div>
	<div class="col-sm-9">
		<article class="media-body">
		<button type="button" class="close" aria-label="Close" style="margin-left:15px" data-target="#myDeleteModal{$id}" data-toggle="modal">
		  <span aria-hidden="true">Delete</span>
		</button>
       <header><h2 class="media-heading"> <xsl:value-of select="@name"/> </h2></header>
       <div class="item__stars-wrp">
         <!--計算該餐廳的評價 = 所有留言給予的評價之平均-->
         <xsl:variable name="rate" select="sum(Waterball:comment/@rate) div count(Waterball:comment)"/>
         <xsl:for-each select="(//node())[$rate >= position()]">
           <span class="icon-ic icon-category/star item__star"><svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 12 12"><path class="svg-color--primary" fill="#F6AB3F" d="M11.988 5.21c-.052-.275-.27-.488-.545-.534l-3.604-.6L6.63.455C6.542.184 6.287 0 6 0s-.542.184-.632.456L4.16 4.076l-3.603.6c-.275.046-.493.26-.545.533-.052.273.072.55.312.695L3.2 7.63l-1.165 3.493c-.093.28.01.59.25.758.115.08.25.12.382.12.148 0 .295-.05.416-.146L6 9.52l2.917 2.333c.12.098.27.147.416.147.133 0 .267-.04.38-.12.244-.17.346-.478.252-.758L8.8 7.63l2.876-1.725c.24-.144.364-.422.312-.696z"></path></svg></span>
         </xsl:for-each>
       </div>
       <p class="address"><xsl:value-of select="@address"/></p>
	   <p class="typeOfMeal"><xsl:value-of select="@typeOfMeal"/>  </p>
       <p class="price">價位 <xsl:value-of select="@price"/></p>
	   <p class="commentAmount" style="float:left;">留言數 <xsl:value-of select="count(Waterball:comment)"/></p>

		<input type="button" value="查看詳情" class="btn btn-success detailsButton" id="detailsButton{$id}"/>

      
     </article>
	 </div>
    </div>
	
	<div class="modal fade" id="myDeleteModal{$id}" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title">沒密碼還想刪除??</h2>
				</div>
			<div class="modal-body">
		<form  id="modal-form-delete{$id}" method="get" action="delete"  >
							<div class="form-group">
								<input type="password" class="form-control" id="res-passwd" name="password" required="required" placeholder="輸入金鑰"/>
								<input type="hidden" name="delete_id" value="{$id}" />
							  </div>
						 </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-default" form="modal-form-delete{$id}" >刪除</button>
					</div>
				</div>
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>