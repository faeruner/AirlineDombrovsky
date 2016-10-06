<jsp:useBean id="entity" scope="request" type="by.pvt.module3.entity.Airline"/>
<div class="form-group has-feedback row">
    <label for="inputName" class="col-md-1 form-control-label" style="text-align: right;">Name</label>
    <div class="col-md-8">
        <input type="text" class="form-control" id="inputName" maxlength="25" name="name" value="${entity.name}"
               required>
        <div class="help-block with-errors"></div>
    </div>
</div>