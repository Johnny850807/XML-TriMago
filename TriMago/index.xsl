<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
  <xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template  match="/">
	<xsl:param name="sort"/>
		<html>
			<head>
				<title>銘傳找飯吃? TriMaGo</title>
				<link rel="shortcut icon"
					href="http://imgur.com/download/NCCXqIj" />
				<meta charset="utf-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1"/>
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
				<link rel="stylesheet" href="triMago.css"/>
        <script language = "JavaScript"  type="text/javascript" >
          $(function(){
          $(".modal-dropdown-menu li a").click(function(){
          $("#modal-select-btn:first-child").text($(this).text());
          $("#modal-select-btn:first-child").val($(this).text());
          $("#modal-typeOfMeal-input:first-child").val($(this).text());
          });
          });

          function onSubmitValidate(){
            return true;
          }
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
                      <li>
                        <a href="#">無分類</a>
                      </li>
                      <li>
                        <a href="#">早餐</a>
                      </li>
                      <li>
                        <a href="#">午餐</a>
                      </li>
					  <li>
                        <a href="#">甜點、冰</a>
                      </li>
                      <li>
                        <a href="#">早午餐</a>
                      </li>
                      <li>
                        <a href="#">下午茶</a>
                      </li>
                      <li>
                        <a href="#">晚餐</a>
                      </li>
                      <li>
                        <a href="#">宵夜</a>
                      </li>
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
				<xsl:apply-templates select="Waterball:WebSite"/>
			</body>
		</html>

    
	</xsl:template>
  
  <xsl:template match="Waterball:WebSite">
      
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130" id="nav">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" id="logo">
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

    <div class="container-fluid" id="leftSelection">
        <div class="row content">
            <div class="col-sm-9 text-left">
              <form action="index" method="get" id="searchPanelForm">
				<input name="typeOfMeal" id="type-menu-input" type="hidden" />
				<input name="sort" id="sort-menu-input" type="hidden" />
                <div class="container" id="searchPanel">
                    <div class="row" id="searchBar">
                        <input class="flipkart-navbar-input col-xs-11" placeholder="輸入想查詢的餐廳名稱" name="searchInput" />
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
                                    <li><a href="#" class="type-item">早餐</a></li>
                                    <li><a href="#" class="type-item">午餐</a></li>
									<li><a href="#" class="type-item">甜點、冰</a></li>
                                    <li><a href="#" class="type-item">早午餐</a></li>
                                    <li><a href="#" class="type-item">下午茶</a></li>
                                    <li><a href="#" class="type-item">晚餐</a></li>
                                    <li><a href="#" class="type-item">宵夜</a></li>
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
                                </ul>
                            </div>
							
							<button type="button" class="btn btn-info search-btn" data-toggle="modal"  
                                    data-target="#myModal">
                              我要推薦餐廳
                            </button>
							
							<button type="button"  
								class="btn btn-danger search-btn" onclick="location.href='map'">美食地圖→</button>
                    </div>
                </div>
              </form>
                <hr/>
				<xsl:apply-templates select="Waterball:restaurant">
					
					<xsl:sort order="descending" select="sum(Waterball:comment/@rate) div count(Waterball:comment)" data-type="number"/>
									
				</xsl:apply-templates>
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
                    <p>請各位不要留表情唷!!!</p>
                </div>
                <div class="well">
                    <p>請各位不要留表情唷!!!</p>
                </div>
                <div class="well">
                    <p>Please don't leave an emoji in a comment!!!</p>
                </div>
                <div class="well">
                    <p>Please don't leave an emoji in a comment!!!</p>
                </div>
            </div>
        </div>
    </div>

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
						  <p><xsl:value-of select="../@address"/></p>
						  <p>點擊觀看詳情</p>
						</div>
				</div>
		</xsl:if>
		<xsl:if test="position() = 1">
				<div class="item active">
						<a href="detail?id={../@id}"><img src="{../@imageUrl}" alt="{../@name}" style="width:100%;"/></a>
						<div class="carousel-caption">
						  <h2><xsl:value-of select="../@name"/></h2>
						  <p><xsl:value-of select="../@address"/></p>
						  <p>點擊觀看詳情</p>
						</div>
				</div>
		</xsl:if>
  </xsl:template>
  
	<xsl:template  match="Waterball:restaurant">
    <div class="media" id="restaurantList">
	<div class="col-sm-5">
        <div class="media-left">
          <xsl:variable name="imageUrl" select="@imageUrl"/>
          <img src="{$imageUrl}" class="media-object" style="width:250px"/>
        </div>
	</div>
	<div class="col-sm-7">
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
       <p class="price">價位 <xsl:value-of select="@price"/></p>
	   <form action="detail" method="get">
		<xsl:variable name="id" select="@id"/>
		<input type="submit" value="查看詳情" class="btn btn-success detailsButton"/>
		<input type="hidden" value="{$id}" name="id"/>
	   </form>
      
     </div>
	 </div>
    </div>
	</xsl:template>

</xsl:stylesheet>