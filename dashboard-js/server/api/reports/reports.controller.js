'use strict';

var activiti = require('../../components/activiti');

exports.index = function (req, res) {
  var options = {
    path: 'rest/file/downloadTasksData',
    query: req.query,
    contentType: 'text/csv'
  };
  if (req.query.sID_Codepage) {
    var charset = 'windows-1251';
    switch (req.query.sID_Codepage) {
      case 'win1251': 'windows-1251'; break;
    }
    options.contentType = options.contentType + ';charset=' + charset;
  }
  activiti.typedfiledownload(req, res, options);
};

exports.statistic = function (req, res) {
  var options = {
    path: 'rest/file/download_bp_timing',
    query: {
      'sID_BP_Name': req.query.sID_BP_Name,
      'sDateAt': req.query.sDateAt,
      'sDateTo': req.query.sDateTo
    }
  };
  activiti.filedownload(req, res, options);
};

exports.template = function (req, res) {
  var options = {
    path: '/rest/getPatternFile',
    query: {
      'sPathFile': req.query.sPathFile
    }
  };
  activiti.filedownload(req, res, options);
};

