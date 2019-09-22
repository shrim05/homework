<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="col-sm-3 sidenav">
      <h4>Kwon's Homework</h4>
      <ul id="menu" class="nav nav-pills nav-stacked">
        <li id="home" class="active"><a href="<%=request.getContextPath()%>/BuyerController"> Home</a></li>
        <li id="create"><a href="#">CreateBuyer</a></li>
        <li id="readList"><a href="#">ReadBuyerList</a></li>
      </ul><br>
      <div class="input-group">
        <input type="text" id="searchForm" class="form-control" placeholder="Search buyer By Name">
        <span class="input-group-btn">
          <button id="searchBtn" class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
    </div>
