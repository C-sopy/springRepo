<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  <aside >
        <header>
             <span class="fs-4">핵심 교과목</span>
        </header>
      <ul class="list-unstyled ">
      <li class="mb-1" >
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed " data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
          Java
        </button>
        <div class="collapse show" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">클래스</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">인터페이스</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">NetWork</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                  빅데이터 
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Python</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">NumPy</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Pandas</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Tensorflow</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          Orders
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">New</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Processed</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Shipped</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Returned</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          Account
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">New...</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Profile</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Settings</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>

    </aside>
 
   <footer class="mt-5 p-4 text-white text-center mybgColor">
        Page Footer
    </footer>
<script>
	$(function(){
		$('#wbtn').click(function(){
			location ="boardForm";
		});
		
	});
</script>
 </body>
</html>
