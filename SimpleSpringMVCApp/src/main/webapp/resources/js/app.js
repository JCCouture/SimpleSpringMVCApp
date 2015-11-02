/** @jsx React.DOM */

var converter = new Showdown.converter();

var Comment = React.createClass({
    render: function() {
        var rawMarkup = converter.makeHtml(this.props.children.toString());
        return (
            <div class="comment">
                <h2 class="commentAuthor">{this.props.email}</h2>
                <span dangerouslySetInnerHTML={{__html: rawMarkup}} />
            </div>
            );
    }
});

var CommentBox = React.createClass({
    loadCommentsFromServer: React.autoBind(function() {
        $.ajax({
            url: this.props.url,
            success: function(data) {
                this.setState({data: data});
            }.bind(this)
        });
    }),
    handleCommentSubmit: React.autoBind(function(comment) {
        var comments = this.state.data;
        comments.push(comment);
        this.setState({data: comments});
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: this.props.url,
            type: 'POST',
            dataType: "json",
            data: JSON.stringify(comment),
            success: function(data) {
                this.setState({data: data});
            }.bind(this)
        });
    }),
    getInitialState: function() {
        return {data: []};
    },
    componentDidMount: function() {
        this.loadCommentsFromServer();
        setInterval(this.loadCommentsFromServer, this.props.pollInterval);
    },
    render: function() {
        return (
            <div class="commentBox">
                <h1>Comments</h1>
                <CommentList data={this.state.data} />
                <CommentForm onCommentSubmit={this.handleCommentSubmit} />
            </div>
            );
    }
});

var CommentList = React.createClass({
    render: function() {
        var commentNodes = this.props.data.map(function (comment) {
            return <Comment email={comment.email}>{comment.password}</Comment>;
        });
        return <div class="commentList">{commentNodes}</div>;
    }
});

var CommentForm = React.createClass({
    handleSubmit: React.autoBind(function() {
        var email = this.refs.email.getDOMNode().value.trim();
        var password = this.refs.password.getDOMNode().value.trim();
        if (email.length === 0 || password.length === 0) {
            return false;
        }
        this.props.onCommentSubmit({email: email, password: password});
        this.refs.email.getDOMNode().value = '';
        this.refs.password.getDOMNode().value = '';
        return false;
    }),
    render: function() {
        return (
            <form className="commentForm" onSubmit={this.handleSubmit}>
                <input type="text" placeholder="Your name" ref="email" />
                <input type="text" placeholder="Say something..." ref="password" />
                <input type="submit" value="Post" />
            </form>
            );
    }
});

React.renderComponent(
    <CommentBox url="/SimpleSpringMVCApp/comments" pollInterval={2000} />,
    document.getElementById('container')
);