DepartmentModel = can.Model.extend({
    findAll:"GET /departments",
    findOne:"GET /departments/{depId}",
    create:"POST /departments",
    update:"PUT /department/{depId}",
    destroy:"DELETE /departments/{depId}"
},{});