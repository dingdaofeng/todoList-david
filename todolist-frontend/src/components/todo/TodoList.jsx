'use strict';

import React from 'react';
import {Table, Icon} from 'antd';
import { apiClient } from '../../service/apiclient';

const overTimeStyle = {
  color: 'red',
  fontWeight:'bold'
};

const columns = [
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
        width: '50%'
    }, {
        title: 'Due Date',
        dataIndex: 'dueDate',
        key: 'dueDate',
        width: '25%',
        render: (text, record) => (
          <span>
              {record.filter === 'SHOW_TODO' && record.overTime === true? (
                  <span style = {overTimeStyle}>
                      {text}
                  </span>
              ) : <span>{text}</span>}
          </span>
        )
    }, {
        title: 'Operation',
        key: 'operation',
        width: '25%',
        render: (text, record) => (
            <span>
                {record.filter === 'SHOW_TODO' ? (
                    <span>
                        <a href="#" onClick={() => record.handleFinish(record.id)}>Finish</a>
                        <span className="ant-divider"></span>
                    </span>
                ) : null}
                <a href="#" onClick={() => record.handleDelete(record.id)}>Delete</a>
            </span>
        )
    }
];

class TodoList extends React.Component {
    componentDidMount() {
      let {actions} = this.props;
      let apiEndpoint = 'list';

      apiClient.get(apiEndpoint)
        .then((response)=>{
          actions.freshTodos(response.data);
      })
    }

    render() {
        let {list, actions, filter} = this.props;

        list.map(item => {
            item.handleFinish = index => actions.completeTodo(index);
            item.handleDelete = index => actions.deleteTodo(index);
            item.filter = filter;
        });

        return (<Table columns={columns} dataSource={list} rowKey={i => i.id}/>);
    }
}

export default TodoList;
