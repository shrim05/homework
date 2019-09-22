<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
  <div class="row content">
  		<jsp:include page="/buyer/nav.jsp" />
    <div id="contentArea" class="col-sm-9">
      <h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>I Love Food</h2>
      <h5><span class="glyphicon glyphicon-time"></span> Post by Jane Dane, Sep 27, 2015.</h5>
      <h5><span class="label label-danger">Food</span> <span class="label label-primary">Ipsum</span></h5><br>
      <p>Food is my passion. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <br><br>
      <h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>Officially Blogging</h2>
      <h5><span class="glyphicon glyphicon-time"></span> Post by John Doe, Sep 24, 2015.</h5>
      <h5><span class="label label-success">Lorem</span></h5><br>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <hr>
      <h4>Leave a Comment:</h4>
      <form role="form">
        <div class="form-group">
          <textarea class="form-control" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-success">Submit</button>
      </form>
      <br><br>
      
      <p><span class="badge">2</span> Comments:</p><br>
   </div>
       <!-- Button to Open the Modal -->
  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
    Open modal
  </button>

  <!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Buyer Information</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
          <form id="editForm" action="<%=request.getContextPath()%>/BuyerController" method="post">
        <div class="modal-body">
             <table class="table table-striped">
              <thead>
                <tr>
                  <th>key</th>
                  <th>value</th>
                </tr>
              </thead>
              <tbody>
               	  <tr><td>buyer_add1: </td><td><input type='text'  		name='buyer_add1' value="">
                  <tr><td>buyer_add2: </td><td><input type='text' 		name='buyer_add2' value="">
                  <tr><td>buyer_bank</td><td><input type='text' 		name='buyer_bank' value="">
                  <tr><td>buyer_bankname: </td><td><input type='text' 	name='buyer_bankname' value="">
                  <tr><td>buyer_bankno: </td><td><input type='text' 	name='buyer_bankno' value="">
                  <tr><td>buyer_charger: </td><td><input type='text' 	name='buyer_charger' value="">
                  <tr><td>buyer_comtel: </td><td><input type='text'		name='buyer_comtel' value="">
                  <tr><td>buyer_fax: </td><td><input type='text' 		name='buyer_fax' value="">
                  <tr><td>buyer_id: </td><td><input type='text' 		name='buyer_id' value="">
                  <tr><td>buyer_lgu: </td><td><input type='text' 		name='buyer_lgu' value="">
                  <tr><td>buyer_mail: </td><td><input type='text' 		name='buyer_mail' value="">
                  <tr><td>buyer_name: </td><td><input type='text' 		name='buyer_name' value="">
                  <tr><td>buyer_zip: </td><td><input type='text' 		name='buyer_zip' value="">
              </tbody>
    </table>
        
        </div>
        <!-- Modal footer -->
        <div class="modal-footer">
          <button id="updateBtn" type="button" class="btn btn-info" data-dismiss="modal">modify</button>
          <button id="deleteBtn" type="button" class="btn btn-info" data-dismiss="modal">delete</button>
          <button id="confirmBtn" type="submit" class="btn btn-info" data-dismiss="modal">confirm</button>
          <button id="resetBtn" type="reset" class="btn btn-warn" >reset</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
        </form>  
      </div>
    </div>
  </div>
   
  </div>
</div>
