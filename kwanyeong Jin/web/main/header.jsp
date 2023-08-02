<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<div class="backyard">
    <nav id="navbody">
        <!-- 왼쪽 상단 로고 링크 -->
        <a id="logo-brand" href="main.jsp"><img src="../image/common/itda_logo2.png" style="width: 100px; height: auto;"></a>
        <ul id="right_btns">
            <li id="search">
                <a id="search_btn" onclick="location.href='search.jsp'">
                    <img src="../image/common/search.png" style="width: 30px; height: auto;">
                </a>
            </li>

            <%
                String id = (String) session.getAttribute("id");
                if (id != null && !id.equals("")) {
            %>

            <!-- 로그인이 되어 있는 경우 id출력 -->
            <div id="login_img"><a class="nav-link" href="mypage.jsp"><img src="/../image/common/profile.png"></a></div>
            <div id="logout_tg"><a class="nav-link" href="logout.jsp">로그아웃</a></div>
            <%
            } else {
            %>
            <li id="login_modal">
                <!-- 로그인 버튼 -->
                <button type="button" class="login_btn" data-toggle="modal" data-target="#myModal"
                        style="color: rgb(251, 209, 167); background: rgb(1, 39, 60); border: none;"
                        onmouseover="this.style.background='#FBD1A7'; this.style.color='#01273C';
		 					 this.style.opacity='0.8';"
                        onmouseout="this.style.background='rgb(1, 39, 60)'; this.style.color='rgb(251, 209, 167)';
    						this.style.opacity='1';">로그인
                </button>
                <!-- Modal -->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content" style="background: rgb(1, 39, 60);">
                            <div class="modal-header">
                                <h4 class="modal-title" style="color: rgb(251, 209, 167);
						 border: none;">IT:DA 로그인</h4>
                                <button type="button" class="close" data-dismiss="modal">×</button>
                            </div>
                            <div class="modal-body">
                                <p style="color: rgb(251, 209, 167);">아이디<br>
                                    <input type="text" style="background: rgb(251, 209, 167);
				    			width:100%; height:auto;"><br>
                                </p>
                                <p style="color: rgb(251, 209, 167);">비밀번호<br>
                                    <input type="text" style="background: rgb(251, 209, 167);
							width:100%; height:auto;">
                                </p>
                                <div style="text-align:center;">
                                    <button type="submit" class="btn" style="color: rgb(251, 209, 167);
							background: rgb(1, 39, 60); border: none; text-align:center;
							border: 1px solid #FBD1A7; width: 40%; height: auto;
							margin:auto;">확인
                                    </button>
                                    <button type="cancel" class="btn" style="color: rgb(251, 209, 167);
							background: rgb(1, 39, 60); border: none; text-align:center;
							border: 1px solid #FBD1A7; width: 40%; height: auto;
							margin:auto;">취소
                                    </button>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn" style="background: #FBD1A7;
        					color: #01273C; opacity: 0.8; border: 1px solid #FBD1A7;">회원가입
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal"
                                        style="background: #FBD1A7; color: #01273C; opacity: 0.8;
				    	 	border: 1px solid #FBD1A7;">Close
                                </button>
                            </div>

                        </div>    <!-- modal-content end -->
                    </div>    <!-- modal-dialog end -->
                </div>    <!-- Modal end -->
            </li><!-- login_modal end -->
            <%
                }
            %>
        </ul>
    </nav>
</div>