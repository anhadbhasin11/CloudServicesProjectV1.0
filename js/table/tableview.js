/**
 * Created by vidyakhadsare on 5/8/16.
 */
var React = require('react');
var ReactDOM = require('react-dom');
var _ = require('underscore');
var $ = require('jquery');

var TableView = React.createClass({

  render: function () {
    var _this = this;

    var columnEls = _.map(this.props.columns, function (columnName, index) {
      return (<span className="col" key={index}>{columnName}</span>);
    });

    var rows = _.map(this.props.data, function (rowData) {
      var key;
      var cols = _.map(rowData, function (colData) {
        if (colData.key) {
          key = colData.value;
        }
        return (
            <span className="col">
              {colData.value}
            </span>
        );
      });

      return (
          <div className="row" data-key={key} onClick={_this.onRowClick}>
            {cols}
          </div>
      );
    });

    return (
        <div className="table-container">
          <div className="table" ref="table">
            <div className="row header">
              {columnEls}
            </div>
            {rows}
          </div>
        </div>
    );
  },

  onRowClick: function (evt) {
    this.props.onRowClick(this.props.dataType, evt);
  }


});


module.exports = TableView;