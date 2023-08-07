function setInnerHTML1() {
	const element = document.getElementById('my_div');
	element.innerHTML = `
    <div class="background-wrap">
      <div class="content">
        <a href="https://www.naver.com/"><img src="../image/channel/home1.png" style="width: 220px; height: 240px; margin-right: 20px;"></a>
        <a href="https://www.naver.com/"><img src="../image/channel/home2.png" style="width: 220px; height: 240px; margin-right: 20px;"></a>
        <a href="https://www.naver.com/"><img src="../image/channel/home3.png" style="width: 220px; height: 240px;"></a>
      </div>
    </div>`;
}


function setInnerHTML2() {
	const element = document.getElementById('my_div');
	element.innerHTML = `
    <div class="category-content">
       <table class="table table-bordered">
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 전체 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 고양이_김겨울 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 김민섭 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 책장위고양이 에세이 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 고양이 성격 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 고양이 간식 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 고양이상품 신상소개 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 간식만드는법 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 고양이 장난감 언박싱 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 츄르 소개 </a></td>
        </tr>
        <tr>
          <td><a href="https://www.naver.com/" class='test'> 뚱냥이 관리법 </a></td>
        </tr>
      </table>
    </div>`;
}

// 초기 내용을 "홈" 섹션으로 설정하는 함수
function initializeDefaultContent() {
	const element = document.getElementById('my_div');
	element.innerHTML = `
      <div class="background-wrap">
        <div class="content">
           <a href="https://www.naver.com/"><img src="../image/channel/home1.png" style="width: 220px; height: 240px; margin-right: 20px;"></a>
        <a href="https://www.naver.com/"><img src="../image/channel/home2.png" style="width: 220px; height: 240px; margin-right: 20px;"></a>
        <a href="https://www.naver.com/"><img src="../image/channel/home3.png" style="width: 220px; height: 240px;"></a>
        </div>
      </div>`;

	// "홈" 버튼을 활성화 상태로 설정
	const homeButton = document.querySelector('.bt-item[value="홈"]');
	homeButton.classList.add('on');
}

// 페이지 로드될 때 함수 호출하여 초기 내용 및 버튼 설정
window.onload = function() {
	initializeDefaultContent();
};