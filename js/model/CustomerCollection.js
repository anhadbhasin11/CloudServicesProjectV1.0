/**
 * Created by vidyakhadsare on 5/8/16.
 */
var Backbone = require('backbone');
var Constants = require('../utils/Constants');

var _ = require('underscore');

var DP = {
  CUST_NO: "customerNumber",
  F_NAME: "firstName",
  L_NAME: "lastName",
  CONT_NUM: "contactNumber",
  ADDR_1: "addressLine1",
  ADDR_2: "addressLine2",
  CITY: "city",
  STATE: "state",
  POST_CODE: "postalCode",
  COUNTRY: "country",
  SALES_NO: "saleRepEmpNo",
  CR_LIMIT: "creditLimit"
};

var CustomerModel = Backbone.Model.extend({
  DP: DP,

  getAddress: function () {
    return this.get(DP.ADDR_1) + ', ' + this.get(DP.ADDR_2);
  },

  getDataColumns: function () {
    return [
      { key: false, value: this.get(DP.CUST_NO)},
      { key: false, value: this.get(DP.F_NAME)},
      { key: false, value: this.get(DP.L_NAME)},
      { key: false, value: this.get(DP.CONT_NUM)},
      { key: false, value: this.getAddress()},
      { key: false, value: this.get(DP.STATE)},
      { key: false, value: this.get(DP.POST_CODE)},
      { key: false, value: this.get(DP.COUNTRY)},
      { key: false, value: this.get(DP.CR_LIMIT)}
    ];
  }
});

var CustomerCollection = Backbone.Collection.extend({
  model: CustomerModel,

  url: Constants.URLS.CUSTOMERS,

  getProperties: function () {
    return [
      'No',
      'FName',
      'LName',
      'Contact',
      'Address',
      'State',
      'Postal Code',
      'Country',
      'Credit Limit'
    ];
  },

  getData: function (filter) {
    var models = this.models;
    if (filter) {
      models = this.where(filter);
    }

    var data = [];

    _.each(models, function (model) {
      data.push(model.getDataColumns());
    });
    return data;
  }
});

module.exports = CustomerCollection;