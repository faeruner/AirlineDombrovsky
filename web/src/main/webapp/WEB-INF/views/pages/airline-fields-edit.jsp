<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.Airline"/>
<div class="form-group row">
    <label for="inputName" class="col-md-1 form-control-label"
           style="text-align: right;">Name</label>
    <div class="col-md-11">
        <input type="text" class="form-control" id="inputName"
               name="name" value="${entity.name}">
    </div>
</div>
