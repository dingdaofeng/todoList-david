'use strict';

import React from 'react';
import { Form, Input, Button, DatePicker } from 'antd';
import { apiClient } from '../../service/apiclient';

const FormItem = Form.Item;

class UpdateForm extends React.Component {
    constructor() {
        super();
        this.state = {
            timeError: false,
            item: {}
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        this.props.form.validateFields((errors, values) => {
            if (errors) {
            } else {
                values.id = this.props.id;
                this.props.handleUpdate(values);
                alert("update item completed");
            }
        });
    }

    completeTodoItem(e) {
      this.props.handleComplete(this.props.id);

      this.setState({
        done: true
      });

      alert("item is done");
    }

    componentDidMount() {

      let apiEndpoint = 'get/' + this.props.id;

      apiClient.get(apiEndpoint)
        .then((response)=>{
          this.setState({
            done: response.data.done
          });

          this.props.form.setFieldsValue({
            name: response.data.name,
            description: response.data.description,
            dueDate: response.data.dueDate? new Date(response.data.dueDate) : null
          })
      })
    }

    render() {
      const {getFieldProps} = this.props.form;

      const dueDateProps = getFieldProps('dueDate', {
          rules: [
              {
                  required: false,
                  type: 'object'
              }
          ]
      });
      const nameProps = getFieldProps('name', {
          rules: [
              {
                  required: true
              }
          ]
      });
      const descriptionProps = getFieldProps('description', {
          rules: [
              {
                  required: false
              }
          ]
      });

       const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 14
            }
        };

        const locale = {
            lang: {
                placeholder: 'Please select time'
            }
        };

        let {done} = this.state;
        let statusStr = done ? "Done" : "Pending";

        return (
            <Form horizontal onSubmit={this.handleSubmit}>
                <FormItem {...formItemLayout} label='Name' required>
                    <Input placeholder='Please input name' {...nameProps}/>
                </FormItem>
                <FormItem {...formItemLayout} label='description'>
                    <Input type='textarea' rows="5" maxLength="500" placeholder='Please input description' {...descriptionProps}/>
                </FormItem>
                <FormItem {...formItemLayout} label='status'>
                    <Input value={statusStr} disabled={true}/>
                </FormItem>
                <FormItem {...formItemLayout} label='Date'>
                    <DatePicker locale={locale} {...dueDateProps}/>
                </FormItem>
                <FormItem wrapperCol={{ span: 16, offset: 6 }} style={{ marginTop: 24 }}>
                    <Button type='primary' htmlType='submit'>Confirm</Button>
                    {!done && <Button type='ghost' onClick={() => this.completeTodoItem()} style={{ marginLeft: 15 }}>Finish</Button>}
                    <Button type='ghost' onClick={() => this.props.handleTodoLink()} style={{ marginLeft: 15 }}>Back</Button>
                </FormItem>
            </Form>
            );
    }
}

export default Form.create()(UpdateForm);
